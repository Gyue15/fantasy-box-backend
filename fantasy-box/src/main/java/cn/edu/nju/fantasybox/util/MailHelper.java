package cn.edu.nju.fantasybox.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by shea on 2018/2/12.
 */
@Component
public class MailHelper {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    private final Logger logger = LoggerFactory.getLogger(MailHelper.class);

    /**
     * 发送邮件
     *
     * @param to      接收方
     * @param title   邮件主题
     * @param content 邮件内容
     */
    public void sendMail(String to, String title, String content) {
        Properties props = new Properties(); //可以加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", host);//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        session.setDebug(true); //有他会打印一些调试信息。
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        try {
            message.setFrom(new InternetAddress("fantasy-box <" + from + ">"));
//            message.setFrom(new InternetAddress(from));//设置发件人的地址
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO
            message.setSubject(title);//设置标题
            //设置信件内容
            message.setContent(content, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
            message.setSentDate(new Date());//设置发信时间
            message.saveChanges();//存储邮件信息
            //发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(from, password);
            transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.close();
        } catch (MessagingException e) {
            logger.error("context",e);
        }

    }

}
