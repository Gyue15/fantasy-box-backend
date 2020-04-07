package cn.edu.nju.fantasybox.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MailHelperTest {
//    @Autowired
    private MailHelper mailHelper;

//    @Autowired
    private FreemarkerHelper freemarkerHelper;

    public MailHelperTest(MailHelper mailHelper, FreemarkerHelper freemarkerHelper) {
        this.mailHelper = mailHelper;
        this.freemarkerHelper = freemarkerHelper;
    }

//    @Test
    public void testSendHtmlMail() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "abcdefg");
        String text = freemarkerHelper.getMailText(map, "verify-code.ftl");
        mailHelper.sendMail("gyue15@163.com", "验证码", text);

    }
}
