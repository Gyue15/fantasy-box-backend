package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.model.UserModel;

import java.util.List;


public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    void register(String username,String password,String email);

    void verifyEmail(String email);

    UserModel login(String username, String password);

    String generateRandomCode();

    void activateAccount(String email,String token);
}
