package cn.edu.nju.fantasybox;

import cn.edu.nju.fantasybox.controller.*;
import cn.edu.nju.fantasybox.mapper.ProductMapper;
import cn.edu.nju.fantasybox.mapper.ProductMapperTest;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.service.FileService;
import cn.edu.nju.fantasybox.service.ProductService;
import cn.edu.nju.fantasybox.service.UserService;
import cn.edu.nju.fantasybox.service.impl.FileServiceTest;
import cn.edu.nju.fantasybox.service.impl.ProductServiceTest;
import cn.edu.nju.fantasybox.service.impl.UserServiceTest;
import cn.edu.nju.fantasybox.util.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
class FantasyBoxApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailHelper mailHelper;
    @Autowired
    private FreemarkerHelper freemarkerHelper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RSAEncrypt rsaEncrypt;
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    FileService fileService;
    @Autowired
    FileController fileController;
    @Autowired
    ProductController productController;
    @Autowired
    UserController userController;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
    }

    @Test
    void contextLoads() {
        ProductServiceTest productServiceTest = new ProductServiceTest(productService);
        productServiceTest.testGetAll();
        productServiceTest.testGetProduct();
        productServiceTest.testGetMyProduct();
        productServiceTest.testPostProduct();
        productServiceTest.testSearch();
        MailHelperTest mailHelperTest = new MailHelperTest(mailHelper,freemarkerHelper);
        mailHelperTest.testSendHtmlMail();
        FreemarkerHelperTest freemarkerHelperTest = new FreemarkerHelperTest(freemarkerHelper);
        freemarkerHelperTest.testGetTemplateText();
        ProductMapperTest productMapperTest = new ProductMapperTest(productMapper,userMapper);
        productMapperTest.findProductByTagTest();
        productMapperTest.testFindProductByUserId();
        productMapperTest.testInsertProduct();
        UserServiceTest userServiceTest = new UserServiceTest(userService,tokenHelper);
        userServiceTest.testGetAllUsers();
        userServiceTest.testGetUser();
        userServiceTest.testToken();
        userServiceTest.testRegister();
        userServiceTest.testModify();
        EncryptTest encryptTest = new EncryptTest(rsaEncrypt);
        encryptTest.test();
        TagConvertTest tagConvertTest = new TagConvertTest();
        tagConvertTest.test();
        FileServiceTest fileServiceTest = new FileServiceTest(fileService);
        fileServiceTest.test();
        FileControllerTest fileControllerTest = new FileControllerTest(mockMvc,fileController);
        fileControllerTest.testUpload();
        fileControllerTest.testGetFile();
        ProductControllerTest productControllerTest = new ProductControllerTest(productController);
        productControllerTest.testGet();
        productControllerTest.testPost();
        AuthentionTest authentionTest = new AuthentionTest(productController);
        authentionTest.test();
//        UserControllerTest userControllerTest = new UserControllerTest(userController);
//        userControllerTest.test();

    }

}
