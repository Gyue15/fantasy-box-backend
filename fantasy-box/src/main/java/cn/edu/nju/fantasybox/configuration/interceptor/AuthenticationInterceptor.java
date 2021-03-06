package cn.edu.nju.fantasybox.configuration.interceptor;

import cn.edu.nju.fantasybox.configuration.annotation.Authentication;
import cn.edu.nju.fantasybox.model.ResultEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


public class AuthenticationInterceptor implements HandlerInterceptor {
    @Value("${cross-authentication}")
    private boolean crossAuthentication;
    @Value("${testUser}")
    private Long testUser;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) {
        System.out.println(httpServletRequest.getRequestURI());
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (!method.isAnnotationPresent(Authentication.class)) {
            return true;
        }
        Authentication authentication = method.getAnnotation(Authentication.class);
        if (!authentication.required()) {
            return true;
        }
        HttpSession httpSession = httpServletRequest.getSession();
        System.out.println("session: " + httpSession.getId());
        Boolean isLogin = (Boolean) httpSession.getAttribute("isLogin");
        if (isLogin == null || !isLogin) {
            if(crossAuthentication){ //供测试环境绕过权限验证
                httpSession.setAttribute("isLogin", true);
                httpSession.setAttribute("userId", testUser);
            }else {
                throw new BusinessException(ResultEnums.UNAUTHORIZED);
            }
        }
        return true;
    }
}
