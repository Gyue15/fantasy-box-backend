package cn.edu.nju.fantasybox.service.impl;

import cn.edu.nju.fantasybox.entity.UserEntity;
import cn.edu.nju.fantasybox.interceptor.BusinessException;
import cn.edu.nju.fantasybox.mapper.UserMapper;
import cn.edu.nju.fantasybox.model.ResultEnums;
import cn.edu.nju.fantasybox.model.UserModel;
import cn.edu.nju.fantasybox.service.UserService;
import cn.edu.nju.fantasybox.util.FreemarkerHelper;
import cn.edu.nju.fantasybox.util.MailHelper;
import cn.edu.nju.fantasybox.util.RSAEncrypt;
import cn.edu.nju.fantasybox.util.TokenHelper;
import com.nimbusds.jose.JOSEException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final FreemarkerHelper freemarkerHelper;

    private final MailHelper mailHelper;

    private final TokenHelper tokenHelper;

    private final DozerBeanMapper dozerBeanMapper;

    private final RSAEncrypt rsaEncrypt;

    @Value("${active-account-url}")
    private String activeAccountUrl;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, FreemarkerHelper freemarkerHelper, MailHelper mailHelper, TokenHelper tokenHelper, DozerBeanMapper dozerBeanMapper, RSAEncrypt rsaEncrypt) {

        this.userMapper = userMapper;
        this.freemarkerHelper = freemarkerHelper;
        this.mailHelper = mailHelper;
        this.tokenHelper = tokenHelper;
        this.dozerBeanMapper = dozerBeanMapper;
        this.rsaEncrypt = rsaEncrypt;
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
        if(userMapper.findByEmail(email)!=null){
            throw new BusinessException(ResultEnums.EMAIL_OCCUPIED);
        }
        if(userMapper.findByUserName(username)!=null){
            throw new BusinessException(ResultEnums.USERNAME_OCCUPIED);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(rsaEncrypt.encrypt(password));
//        userEntity.setPassword(RSAEncrypt.encrypt(password));
        userEntity.setUsername(username);
        String token = tokenHelper.getToken(userEntity.getId());
        userEntity.setToken(token);
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("url",activeAccountUrl);
        data.put("email",email);
        String content = freemarkerHelper.getMailText(data,"activate-account.ftl");
        mailHelper.sendMail(email,"激活账户",content);
        userMapper.insertUser(userEntity);
    }

    @Override
    public void verifyEmail(String email) {
        //检查邮箱是否已经存在 todo
        if(userMapper.findByEmail(email)!=null){
            throw new BusinessException(ResultEnums.EMAIL_OCCUPIED);
        }
        Map<String,Object> map = new HashMap<>();
        String code = generateRandomCode();
        map.put("code",code);
        String content = freemarkerHelper.getMailText(map,"verify-code.ftl");
        mailHelper.sendMail(email,"验证码",content);
        //存储邮箱和验证码
//        userMapper.insertEmail(email,code);
    }

    @Override
    public UserModel login(String username, String password) {
        UserEntity userEntity = userMapper.findByUserName(username);
        if(userEntity==null || !rsaEncrypt.decrypt(userEntity.getPassword()).equals(password)){
//        if(userEntity==null || !RSAEncrypt.decrypt(userEntity.getPassword()).equals(password)){
            throw new BusinessException(ResultEnums.LOGIN_ERROR);
        }
        if(!userEntity.getActivated()){
            throw new BusinessException(ResultEnums.NOT_ACTIVATED);
        }
        String token = tokenHelper.getToken(userEntity.getId());
        userEntity.setToken(token);
        userMapper.saveUser(userEntity);
        return dozerBeanMapper.map(userEntity,UserModel.class);
    }

    @Override
    public String generateRandomCode(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            //获取指定长度的字符串中任意一个字符的索引值
            int idx=random.nextInt(str.length());
            //根据索引值获取对应的字符
            char c = str.charAt(idx);
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    @Override
    public void activateAccount(String email, String token) {
        UserEntity userEntity = userMapper.findByEmail(email);
        if(userEntity==null){
            throw new BusinessException(ResultEnums.USER_NOT_FOUND);
        }
        if(!userEntity.getToken().equals(token)){
            throw new BusinessException(ResultEnums.ACTIVATE_FAIL);
        }
        try {
            tokenHelper.verifyToken(token);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        long userId = userEntity.getId();
        userEntity = new UserEntity();
        userEntity.setActivated(true);
        userEntity.setId(userId);
        userMapper.saveUser(userEntity);
    }
}
