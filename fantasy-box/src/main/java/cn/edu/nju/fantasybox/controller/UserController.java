package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.configuration.annotation.Authentication;
import cn.edu.nju.fantasybox.model.ResponseData;
import cn.edu.nju.fantasybox.util.ResponseDataUtil;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.model.UserModel;
import cn.edu.nju.fantasybox.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get-all-users")
    public ResponseData getAllUsers() {
        return ResponseDataUtil.buildSuccess(this.userService.getAllUsers());
    }

    @GetMapping("get-user")
    public ResponseData getUser(@RequestParam("id") long id) {
        return ResponseDataUtil.buildSuccess(this.userService.getUser(id));
    }

    @GetMapping("get-my-qr-code")
    @Authentication
    public ResponseData getMyQRCode(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildStringSuccess(this.userService.getUser(userId).getQrCodeUrl());
    }

    @PostMapping("register")
    public ResponseData register(String username, String password, String email) {
        logger.info("username: " + username + " password:" + password + " email: " + email);
        userService.register(username, password, email);
        return ResponseDataUtil.buildSuccess(ResultEnums.ACTIVATE_EMAIL_SEND);
    }

    @PostMapping("login")
    public ResponseData login(String username, String password, HttpServletRequest request) {
        UserModel userModel = userService.login(username, password);
        HttpSession httpSession = request.getSession();
        System.out.println("login: " + httpSession.getId());
        httpSession.setAttribute("isLogin", true);
        httpSession.setAttribute("userId", userModel.getId());
        logger.info(httpSession.getId());
        return ResponseDataUtil.buildSuccess(userModel);
    }

    /**
     * 激活账号，通过激活链接发送的请求
     *
     * @param token 激活码
     * @param email 会员ID
     * @return 如果激活成功，则跳转到登陆界面
     */
    @GetMapping("activate-account")
    public String activateAccount(String token, String email) {
        logger.info("email: " + email + " token: " + token);
        userService.activateAccount(email, token);
        //todo 激活成功的话重定向到登陆页面
        return "账户激活成功";
    }


    @PostMapping("modify-password")
    @Authentication
    public ResponseData modifyPassword(@RequestParam("raw-password") String rawPassword,
                                       @RequestParam("new-password") String newPassword, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(userService.modifyPassword(userId, rawPassword, newPassword));
    }

    @PostMapping("modify-avatar")
    @Authentication
    public ResponseData modifyAvatar(@RequestParam("avatar") MultipartFile avatar, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        System.out.println("modify avatar: " + httpSession.getId());
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(userService.modifyAvatar(userId, avatar));
    }

    @PostMapping("modify-qr")
    @Authentication
    public ResponseData modifyQrCode(@RequestParam("qr-code") MultipartFile qrCode, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        long userId = (Long) httpSession.getAttribute("userId");
        return ResponseDataUtil.buildSuccess(userService.modifyQrCode(userId, qrCode));
    }
}
