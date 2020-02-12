package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.service.UserService;
import cn.edu.nju.fantasybox.util.FreemarkerHelper;
import cn.edu.nju.fantasybox.util.MailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final FreemarkerHelper freemarkerHelper;

    private final MailHelper mailHelper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,FreemarkerHelper freemarkerHelper, MailHelper mailHelper) {

        this.userMapper = userMapper;
        this.freemarkerHelper = freemarkerHelper;
        this.mailHelper = mailHelper;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public UserEntity getUser(long id) {
        return userMapper.select(id);
    }

    @Override
    public void register(String username, String password, String email) {

    }

    @Override
    public void getIdentifyCode(String email) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",989);
        String content = freemarkerHelper.getMailText(map);
        mailHelper.sendHtmlMail(email,"欢迎注册fantasy-box",content);

    }

    @Override
    public void login(String username, String password) {

    }
}
