package com.el;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootScheduledMailApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    /**
     * 简单文本发送
     */
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("主题-来自springboot邮件测试");
        message.setText("梦缘，傻叉.........");
        message.setFrom("304229510@qq.com");
        message.setTo("377581856@qq.com");

        javaMailSender.send(message);
    }

    @Test
    public void contextLoads1() throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("主题-来自springboot邮件测试");
        mimeMessageHelper.setText("<b style='color:red'>测试是否能转换成html格式</b>",true);
        mimeMessageHelper.setFrom("304229510@qq.com");
        mimeMessageHelper.setTo("18603824083@163.com");

        //文件上传
        mimeMessageHelper.addAttachment("图片附件",new File("/Users/jerry.feng/Downloads/timg.jpeg"));
        mimeMessageHelper.addAttachment("附件",new File("/Users/jerry.feng/Downloads/spec sheet.pdf"));

        javaMailSender.send(mimeMessage);
    }

}
