package cn.edu.nju.fantasybox.service.impl;


import static org.junit.Assert.*;

import cn.edu.nju.fantasybox.model.ProductListModel;
import cn.edu.nju.fantasybox.model.ProductModel;
import cn.edu.nju.fantasybox.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Component
public class ProductServiceTest {

//    @Autowired
    private ProductService productService;

    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    //    @Test
    public void testGetProduct() {
        ProductModel productModel = productService.getProduct(1);
        System.out.println(productModel);
        productModel = productService.getProduct(1000);
        System.out.println(productModel);
//        assertNotNull(productModel);
    }

//    @Test
    public void testGetAll(){
        List<ProductListModel> res = productService.getAllProductList(9);
        assertNotNull(res);
        for (ProductListModel productListModel:res) {
            System.out.println(productListModel);
        }

        System.out.println(res.size());

    }

    public void testGetMyProduct(){
        List<ProductModel> res = productService.getMyProduct(1);
        System.out.println(res);

    }

    @Transactional
    public void testPostProduct(){
        File file = new File("/Users/shea/Pictures/timg.jpeg");
        try {
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("f1",inputStream);
            List<String> tags = new ArrayList<>();
            tags.add("tt");
            tags.add("uiu");
            productService.postProduct(multipartFile,multipartFile,"895gi","shudig",tags,1);
            productService.postProduct("1","2","skjfh","shdg",tags,1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testSearch() {
        List<String> keywords = new ArrayList<>();
        keywords.add("rr");
        productService.search(keywords);
    }
}
