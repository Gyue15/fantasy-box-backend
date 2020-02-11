package cn.edu.nju.fantasybox.mapper;


import static org.junit.Assert.*;

import cn.edu.nju.fantasybox.entity.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findProductByTagTest() {
        List<ProductEntity> productEntityList = productMapper.findProductByTag("图片");
        assertNotNull(productEntityList);
        assertNotEquals(0, productEntityList.size());
    }
}
