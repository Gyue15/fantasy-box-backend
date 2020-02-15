package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.model.UserModel;

import java.util.List;


public interface UserService {

    List<UserModel> getAllUsers();

    UserModel getUser(long id);

    void register(String username,String password,String email);

    UserModel login(String username, String password);

    void activateAccount(String email,String token);
}
