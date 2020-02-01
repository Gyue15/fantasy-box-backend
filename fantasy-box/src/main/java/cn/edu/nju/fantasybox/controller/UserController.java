package cn.edu.nju.fantasybox.controller;

import cn.edu.nju.fantasybox.entity.User;
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
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("get-user")
    public User getUser(@RequestParam("id") long id) {
        return this.userService.getUser(id);
    }
}
