package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;

public class UserControllerTest {
    private UserController userController;

    public UserControllerTest(UserController userController) {
        this.userController = userController;
    }

    private final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    public void test(){

        try{
            userController.register("t222","t222","test222@342.com");
            userController.register("t333","t333","test333@342.com");
            MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
            userController.activateAccount("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4MTc2MzE1MjY5MywiZXhwIjoxNTgxODQ5NTUyNjkzfQ.ZFtIB9VzYOd8qPwA1Rhp_0GjMeMn8_wvdM87G5djzN8","shea_wong@163.com");
            userController.activateAccount("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjAsInN0YSI6MTU4NjI2MTkxOTA0NiwiZXhwIjoxNTg2MzQ4MzE5MDQ2fQ.3rrpSHJNUsxR6I7LNoE3m3ohRe-j3ZBh-PuFjksu0V0","mf1932181@smail.nju.edu.cn");

        }catch (BusinessException e){
            logger.error("activateAccount",e);
        }
//        userController.login("wxy","gRUVZVlr8xZ9Qv/setVfJD/gG6d1hJhAzgFu/ZxhbwjimRxaQHgUEsNL1UBO+tC7shO21SIUxQRu2uhJ4oKi56IlNl5xtf1LWoe4ntdtkhxz7wFg8lekXlBksBcIBL9ZzfhnPCDYrQwV+vSmm+B0qLQOslY1/7W9VQ5HLZOfYqw=",mockHttpServletRequest);

    }

}
