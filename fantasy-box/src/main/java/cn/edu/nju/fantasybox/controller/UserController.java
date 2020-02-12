package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get-all-users")
    public List<UserEntity> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("get-user")
    public UserEntity getUser(@RequestParam("id") long id) {
        return this.userService.getUser(id);
    }

    @PostMapping("get-identify-code")
    public void getIdentifyCode(String email){
        userService.getIdentifyCode(email);
    }

    @PostMapping("register")
    public void register(String username,String password,String email,String identifyCode){

    }

    @PostMapping("login")
    public void login(String username,String password){

    }


}
