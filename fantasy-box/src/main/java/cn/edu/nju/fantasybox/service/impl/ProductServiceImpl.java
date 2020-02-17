package cn.edu.nju.fantasybox.service.impl;


import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.entity.ProductEntity;
import cn.edu.nju.fantasybox.entity.TagEntity;
import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.mapper.ProductMapper;
import cn.edu.nju.fantasybox.mapper.TagMapper;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.service.ProductService;
import cn.edu.nju.fantasybox.util.FileHelper;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private final FileHelper fileHelper;

    private static final String HOT = "热门";

    @Value("${file-service.local}")
    private String localPath;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, TagMapper tagMapper, DozerBeanMapper dozerBeanMapper,
                              UserMapper userMapper, FileHelper fileHelper) {
        this.productMapper = productMapper;
        this.tagMapper = tagMapper;
        this.dozerBeanMapper = dozerBeanMapper;
        this.userMapper = userMapper;
        this.fileHelper = fileHelper;
    }

    @Override
    public List<ProductListModel> getAllProductList(int hotNum) {
        logger.info(String.valueOf(hotNum));
        List<ProductListModel> productList = new ArrayList<>();
        // 添加热门产品
        List<ProductEntity> hotEntities = productMapper.findHotProduct(hotNum);
        List<ProductModel> hotModels = new ArrayList<>();
        hotEntities.forEach(productEntity -> hotModels.add(dozerBeanMapper.map(productEntity, ProductModel.class)));
        hotModels.forEach(fileHelper::addUrlPrefix);
        productList.add(new ProductListModel(HOT, hotModels));

        // 找到所有标签
        List<String> tags = tagMapper.selectAllByCount();
        // 找到这些标签对应的产品
        List<ProductEntity> entities = productMapper.findProductByTagList(tags);
        Map<String, List<ProductModel>> modelMap = new HashMap<>(tags.size());
        entities.forEach(productEntity -> {
            List<ProductModel> modelList = modelMap.getOrDefault(productEntity.getSelectTag(), new ArrayList<>());
            ProductModel model = dozerBeanMapper.map(productEntity, ProductModel.class);
            fileHelper.addUrlPrefix(model);
            modelList.add(model);
            modelMap.put(productEntity.getSelectTag(), modelList);
        });
        tags.forEach(tag -> productList.add(new ProductListModel(tag, modelMap.get(tag))));

        return productList;
    }

    @Override
    public ProductModel getProduct(long id) {
        logger.info(String.valueOf(id));
        ProductEntity entity = productMapper.select(id);
        if (entity == null) {
            return null;
        }
        ProductModel model = dozerBeanMapper.map(entity, ProductModel.class);
        fileHelper.addUrlPrefix(model);
        return model;
    }

    @Override
    public List<ProductModel> getMyProduct(long userId) {
        List<ProductEntity> productEntities = productMapper.findProductByUserId(userId);
        if (productEntities == null) {
            return null;
        }
        return productEntities.stream().map(productEntity -> fileHelper.addUrlPrefix(dozerBeanMapper.map(productEntity, ProductModel.class))).collect(Collectors.toList());
    }

    @Override
    public ProductModel postProduct(MultipartFile file, String description, String title, List<String> tags,
                                    long userId) {
        // 文件保存路径
        String filePath = this.localPath + System.currentTimeMillis() + file.getOriginalFilename();
        // 文件url
        String fileUrl = System.currentTimeMillis() + file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                File dest = new File(filePath);

                // 检测是否存在目录
                if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
                    throw new BusinessException(ResultEnums.FILE_UPLOAD_ERROR);
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
                productEntity.setUsername(userEntity.getUsername());
                productMapper.insertProduct(productEntity);
                final long productId = productEntity.getId();
                //存储标签
                List<TagEntity> tagEntities = tags.stream().map(tag -> {
                    TagEntity tagEntity = new TagEntity();
                    tagEntity.setProductId(productId);
                    tagEntity.setTagName(tag);
                    return tagEntity;
                }).collect(Collectors.toList());
                tagMapper.insertAll(tagEntities);

                // 获取更新后的product
                productEntity = productMapper.select(productId);
                return fileHelper.addUrlPrefix(dozerBeanMapper.map(productEntity, ProductModel.class));

            } catch (IOException e) {
                logger.error("context",e);
                throw new BusinessException(ResultEnums.FILE_UPLOAD_ERROR);
            }
        } else {
            throw new BusinessException(ResultEnums.FILE_NOT_FOUND);
        }
    }

    @Override
    public List<ProductModel> search(List<String> keywords) {
        List<ProductEntity> productEntityList = productMapper.search(keywords);
        return productEntityList.stream().map(productEntity -> fileHelper.addUrlPrefix(dozerBeanMapper.map(productEntity, ProductModel.class))).collect(Collectors.toList());
    }
}
