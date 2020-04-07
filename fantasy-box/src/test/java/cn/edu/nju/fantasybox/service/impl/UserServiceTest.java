package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.UserModel;
import cn.edu.nju.fantasybox.service.UserService;
import cn.edu.nju.fantasybox.util.TokenHelper;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class UserServiceTest {
    private UserService userService;
    private TokenHelper tokenHelper;


    public UserServiceTest(UserService userService, TokenHelper tokenHelper) {
        this.userService = userService;
        this.tokenHelper = tokenHelper;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Transactional
    public void testGetUser(){
        UserModel userModel = userService.getUser(1);
        System.out.println(userModel);
    }

    @Transactional
    public void testGetAllUsers(){
        List<UserModel> userModels = userService.getAllUsers();

    }

    @Transactional
    public void testToken(){
        String token = tokenHelper.getToken(1);
        try {
            tokenHelper.verifyToken(token);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
//        userService.register("t1","t1","mf1932181@smail.nju.edu.cn");
//        userService.login("test00","123456");

    }

    @Transactional
    public void testRegister(){
        userService.register("t9","t9","888888888@afj.com");
        userService.login("t2","t2");
        userService.activateAccount("test333@342.com","eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4NjI2NTIzOTE3MSwiZXhwIjoxNTg2MzUxNjM5MTcxfQ.2ccY8wDwvNe8vGKRiqFBEUiaJzkILvwsKS2Y4r3QLYo");
    }

    public void testModify() {
        File file = new File("/Users/shea/Pictures/timg.jpeg");

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("f1",inputStream);
            userService.modifyAvatar(1l,multipartFile);
            userService.modifyQrCode(1l,multipartFile);
            userService.modifyPassword(23l,"t111","t11");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
