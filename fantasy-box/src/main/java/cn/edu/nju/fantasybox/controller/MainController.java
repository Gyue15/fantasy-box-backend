package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 激活账号，通过激活链接发送的请求
     * @param token 激活码
     * @param email 会员ID
     * @return 如果激活成功，则跳转到登陆界面
     */
    @GetMapping("activate-account")
    public String activateAccount(String token,String email){
        logger.info("email: "+email+" token: "+token);
        userService.activateAccount(email,token);
        //todo 激活成功的话跳转到登陆页面
        return "账户激活成功";
    }
}
