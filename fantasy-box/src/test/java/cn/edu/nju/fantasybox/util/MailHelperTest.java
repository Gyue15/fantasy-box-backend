package cn.edu.nju.fantasybox.util;

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
    @Test
    public void testSendHtmlMail(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",9287);
        String text = freemarkerHelper.getMailText(map);
//        mailHelper.sendHtmlMail("shea_wong@163.com","欢迎注册fantasy-box",text);
        mailHelper.sendHtmlMail("MF1932181@smail.nju.edu.cn","欢迎注册fantasy-box",text);

    }
}
