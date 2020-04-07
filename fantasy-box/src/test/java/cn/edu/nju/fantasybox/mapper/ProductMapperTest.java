package cn.edu.nju.fantasybox.mapper;


import static org.junit.Assert.*;

import cn.edu.nju.fantasybox.entity.ProductEntity;
import cn.edu.nju.fantasybox.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProductMapperTest {

//    @Autowired
    private ProductMapper productMapper;
//    @Autowired
    private UserMapper userMapper;

    public ProductMapperTest(ProductMapper productMapper, UserMapper userMapper) {
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }

    //    @Test
    public void findProductByTagTest() {
        List<ProductEntity> productEntityList = productMapper.findProductByTag("图片");
        assertNotNull(productEntityList);
//        assertNotEquals(0, productEntityList.size());
    }

//    @Test
    @Transactional
    public void testFindProductByUserId(){
        List<ProductEntity> productEntities = productMapper.findProductByUserId(2);
        assertNotNull(productEntities);
        assertNotEquals(0,productEntities.size());
    }

//    @Test
    @Transactional
    public void testInsertProduct(){
        int i = 3;
        UserEntity userEntity = userMapper.select(2);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setUsername(userEntity.getUsername());
        productEntity.setUserId(userEntity.getId());
        productEntity.setUserAvatar(userEntity.getAvatarUrl());
        productEntity.setProductName("pro"+i);
        productEntity.setDescription("des"+i);
        productEntity.setFileUrl("fu"+i);
        int res = productMapper.insertProduct(productEntity);
        System.out.println(res);
    }
}
