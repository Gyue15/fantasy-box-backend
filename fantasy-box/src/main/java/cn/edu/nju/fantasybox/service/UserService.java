package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.entity.UserEntity;

import java.util.List;


public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);

    void register(String username,String password,String email);

    void getIdentifyCode(String email);

    void login(String username,String password);
}
