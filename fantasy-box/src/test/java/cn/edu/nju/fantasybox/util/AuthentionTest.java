package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.configuration.interceptor.AuthenticationInterceptor;
import cn.edu.nju.fantasybox.controller.ProductController;
import cn.edu.nju.fantasybox.controller.UserController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthentionTest {
    private AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();
    private ProductController productController;

    public AuthentionTest(ProductController productController) {
        this.productController = productController;
    }

    public void test(){
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        HttpSession session = new MockHttpSession();
        session.setAttribute("userId",1l);
        mockHttpServletRequest.setSession(session);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        try {
            Class<?> c = Class.forName("cn.edu.nju.fantasybox.controller.ProductController");
            Method method = c.getMethod("getProduct",Long.class);
            HandlerMethod handlerMethod = new HandlerMethod(productController,method);
            authenticationInterceptor.preHandle(mockHttpServletRequest,mockHttpServletResponse,handlerMethod);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

}
