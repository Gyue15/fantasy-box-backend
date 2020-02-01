package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.entity.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);
}
