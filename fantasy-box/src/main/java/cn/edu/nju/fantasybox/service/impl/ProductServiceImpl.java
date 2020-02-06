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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // 找到所有标签
        List<String> tags = tagMapper.selectAllByCount();
        // 找到这些标签对应的产品
        List<ProductEntity> entities = productMapper.findProductByTagList(tags);
        Map<String, List<ProductModel>> modelMap = new HashMap<>(tags.size());
        entities.forEach(productEntity -> {
            List<ProductModel> modelList = modelMap.getOrDefault(productEntity.getSelectTag(), new ArrayList<>());
            modelList.add(dozerBeanMapper.map(productEntity, ProductModel.class));
            modelMap.put(productEntity.getSelectTag(), modelList);
        });
        tags.forEach(tag -> productList.add(new ProductListModel(tag, modelMap.get(tag))));

        return productList;
    }

    @Override
    public ProductModel getProduct(long id) {
        ProductEntity entity = productMapper.select(id);
        if (entity == null) {
            return null;
        }
        return dozerBeanMapper.map(entity, ProductModel.class);
    }
}
