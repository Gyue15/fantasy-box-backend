package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.entity.User;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User getUser(long id) {
        return userMapper.select(id);
    }
}
