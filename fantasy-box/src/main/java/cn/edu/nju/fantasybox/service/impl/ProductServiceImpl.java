package cn.edu.nju.fantasybox.service.impl;


import cn.edu.nju.fantasybox.entity.ProductEntity;
import cn.edu.nju.fantasybox.mapper.ProductMapper;
import cn.edu.nju.fantasybox.mapper.TagMapper;
import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.service.ProductService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final TagMapper tagMapper;

    private final DozerBeanMapper dozerBeanMapper;

    private static final String HOT = "热门";

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, TagMapper tagMapper, DozerBeanMapper dozerBeanMapper) {
        this.productMapper = productMapper;
        this.tagMapper = tagMapper;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public List<ProductListModel> getAllProductList(int hotNum) {
        List<ProductListModel> productList = new ArrayList<>();
        // 添加热门产品
        List<ProductEntity> hotEntities = productMapper.findHotProduct(hotNum);
        List<ProductModel> hotModels = new ArrayList<>();
        hotEntities.forEach(productEntity -> hotModels.add(dozerBeanMapper.map(productEntity, ProductModel.class)));
        productList.add(new ProductListModel(HOT, hotModels));

        System.out.println(hotEntities);

        // 找到所有标签
        List<String> tags = tagMapper.selectAllByCount();
        // 找到这些标签对应的产品
        tags.forEach(tag -> {
            List<ProductEntity> entities = productMapper.findProductByTypes(tag);
            List<ProductModel> models = new ArrayList<>();
            entities.forEach(productEntity -> models.add(dozerBeanMapper.map(productEntity, ProductModel.class)));
            productList.add(new ProductListModel(tag, models));
            System.out.println(entities);
        });

        return productList;
    }
}
