package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.UploadModel;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {
    ProductController productController;

    public ProductControllerTest(ProductController productController) {
        this.productController = productController;
    }

    public void testGet(){
        productController.getAllProductList(9);
        productController.getAllProductList(null);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        HttpSession session = new MockHttpSession();
        session.setAttribute("userId",1l);
        mockHttpServletRequest.setSession(session);
        productController.getMyProduct(mockHttpServletRequest);
        productController.getProduct(1l);
        productController.search("aa");
    }

    public void testPost(){
        File file = new File("/Users/shea/Pictures/timg.jpeg");
        try {
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("f1",inputStream);
            List<String> tags = new ArrayList<>();
            tags.add("tt");
            tags.add("uiu");
            MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
            HttpSession session = new MockHttpSession();
            session.setAttribute("userId",1l);
            mockHttpServletRequest.setSession(session);
            productController.postProduct(multipartFile,multipartFile,"895gi","shudig",tags,mockHttpServletRequest);
            productController.postProduct("1","2","skjfh","shdg",tags,mockHttpServletRequest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
