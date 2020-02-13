package cn.edu.nju.fantasybox.util;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerHelper {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    //通过模板构造邮件内容，参数username将替换模板文件中的${username}标签。
    public String getMailText(Map map,String template){
        String htmlText="";
        try {
            //通过指定模板名获取FreeMarker模板实例
            Template tpl=freeMarkerConfigurer.getConfiguration().getTemplate(template);
            //解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。
            htmlText= FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return htmlText;
    }
}
