package cn.edu.nju.fantasybox.service.impl;


import cn.edu.nju.fantasybox.configuration.FilePathConfig;
import cn.edu.nju.fantasybox.entity.ProductEntity;
import cn.edu.nju.fantasybox.entity.TagEntity;
import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.exception.InvalidRequestException;
import cn.edu.nju.fantasybox.exception.MethodFailureException;
import cn.edu.nju.fantasybox.mapper.ProductMapper;
import cn.edu.nju.fantasybox.mapper.TagMapper;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.service.ProductService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final TagMapper tagMapper;

    private final DozerBeanMapper dozerBeanMapper;

    private final UserMapper userMapper;

    private static final String HOT = "热门";

    @Autowired
    private FilePathConfig filePathConfig;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, TagMapper tagMapper, DozerBeanMapper dozerBeanMapper,UserMapper userMapper) {
        this.productMapper = productMapper;
        this.tagMapper = tagMapper;
        this.dozerBeanMapper = dozerBeanMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<ProductListModel> getAllProductList(int hotNum) {
        List<ProductListModel> productList = new ArrayList<>();
//      添加热门产品
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

    @Override
    public List<ProductModel> getMyProduct(long userId) {
        List<ProductEntity> productEntities = productMapper.findProductByUserId(userId);
        if(productEntities==null){
            return null;
        }
        return productEntities.stream()
                .map(productEntity -> dozerBeanMapper.map(productEntity,ProductModel.class)).collect(Collectors.toList());
    }

    @Override
    public ProductModel postProduct(MultipartFile file, String description, String title, List<String> tags, long userId) {
        // 文件保存路径
        String filePath = filePathConfig.getLocalPath()+ System.currentTimeMillis()+file.getOriginalFilename();
        // 文件url
        String fileUrl = filePathConfig.getUrlPrefix() + System.currentTimeMillis()+file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                File dest = new File(filePath);

                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }

                file.transferTo(dest);
                //将本次产品发布存到数据库
                UserEntity userEntity = userMapper.select(userId);
                ProductEntity productEntity = new ProductEntity();
                productEntity.setFileUrl(fileUrl);
                productEntity.setDescription(description);
                productEntity.setProductName(title);
                productEntity.setUserAvatar(userEntity.getAvatarUrl());
                productEntity.setUserId(userId);
                productEntity.setUserName(userEntity.getUserName());
                productMapper.insertProduct(productEntity);
                final long productId = productEntity.getId();
                //存储标签
                List<TagEntity> tagEntities = tags.stream().map(tag->{
                    TagEntity tagEntity = new TagEntity();
                    tagEntity.setProductId(productId);
                    tagEntity.setTagName(tag);
                    return tagEntity;
                }).collect(Collectors.toList());
                tagMapper.insertAll(tagEntities);
                ProductModel productModel = dozerBeanMapper.map(productEntity,ProductModel.class);
                productModel.setTagList(tags);
                return productModel;

            } catch (IOException e) {
                e.printStackTrace();
                throw new MethodFailureException("文件上传出错");
            }
        }else {
            throw new InvalidRequestException("文件不存在");
        }
//        return null;
    }
}
