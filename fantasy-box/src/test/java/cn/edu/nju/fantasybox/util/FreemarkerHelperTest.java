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
public class FreemarkerHelperTest {
    @Autowired
    private FreemarkerHelper freemarkerHelper;
    @Test
    public void testGetTemplateText(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",9287);
        String text = freemarkerHelper.getMailText(map);
        System.out.println(text);
    }
}
