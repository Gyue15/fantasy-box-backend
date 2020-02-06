package cn.edu.nju.fantasybox.service;

import cn.edu.nju.fantasybox.entity.UserEntity;

import java.util.List;


public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long id);
}
