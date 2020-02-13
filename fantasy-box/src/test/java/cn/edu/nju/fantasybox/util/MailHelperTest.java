package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailHelperTest {
    @Autowired
    private MailHelper mailHelper;

    @Autowired
    private FreemarkerHelper freemarkerHelper;

    @Autowired
    private UserService userService;

    @Test
    public void testSendHtmlMail(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",userService.generateRandomCode());
        String text = freemarkerHelper.getMailText(map,"verify-code.ftl");
        mailHelper.sendMail("shea_wong@163.com","验证码",text);

    }
}
