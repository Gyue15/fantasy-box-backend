package cn.edu.nju.fantasybox.service;


import static org.junit.Assert.*;

import cn.edu.nju.fantasybox.model.ProductModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetProduct() {
        ProductModel productModel = productService.getProduct(1);
        System.out.println(productModel);
        assertNotNull(productModel);
    }
}
