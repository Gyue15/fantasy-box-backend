package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.model.ResponseData;
import cn.edu.nju.fantasybox.model.ResponseDataUtil;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.model.UserModel;
import cn.edu.nju.fantasybox.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public List<UserEntity> getAllUsers(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        session.getCreationTime();
//        System.out.println(session.getId());
        return this.userService.getAllUsers();
    }

    @GetMapping("get-user")
    public UserEntity getUser(@RequestParam("id") long id) {
        return this.userService.getUser(id);
    }

    @PostMapping("verify-email")
    public void verifyEmail(String email){
        userService.verifyEmail(email);
    }

    @PostMapping("register")
    public ResponseData register(String username, String password, String email){
        logger.info("username: "+username+" password:"+password+" email: "+email);
        userService.register(username,password,email);
        return ResponseDataUtil.buildSuccess(ResultEnums.ACTIVATE_EMAIL_SEND);
    }

    @PostMapping("login")
    public ResponseData login(String username, String password, HttpServletRequest request){
        UserModel userModel = userService.login(username,password);
        return ResponseDataUtil.buildSuccess(userModel);
//        boolean flag = userService.login(username,password);
//        if(flag){
//            HttpSession session = request.getSession();
//            System.out.println(session.getId());
//            //todo 传递sessionID
//            session.setAttribute(username,System.currentTimeMillis());
//        }
    }


}
