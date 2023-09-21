package com.demo.service.impl;

import com.demo.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {
    /**
     *发送邮件
     */

    private String from = "denny20221205@gmail.com";

    private String to = "denny118105@gmail.com";

    private String subject = "测试邮件的主题";

    private String text = "测试定时任务发送的邮件"+System.currentTimeMillis();

    private String text1 = "<img src='https://media.istockphoto.com/id/1430788652/photo/the-river-barneveldse-beek-flows-through-the-agricultural-area-near-the-village-of-stoutenburg.jpg?s=2048x2048&w=is&k=20&c=d06KUy3MsoPmR87m-gESQ4i7Uee8MghFobAgIJ5nTuc='> <a href='https://www.itcast.cn'>点我有惊喜</a>";

    private File file1 = new File("E:\\workspace\\ssmp_demo_02\\target\\学习笔记.rar");
    private File file2 = new File("E:\\workspace\\ssmp_demo_02\\22022312542M617-0-lp.jpg");

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendMail() {
        SimpleMailMessage mailMessage =new SimpleMailMessage();
        mailMessage.setFrom(from+"(办公邮件)");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    /**
     * 发送多部件的邮件
     */
    @Override
    public void sendMineMail() {

        try{
            //创建一个可以发多部件的格式邮件
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //创建具体的实现方式，true表示开启附件传输
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            //添加正题+图片和超链接，true表示开启超文本格式
            mimeMessageHelper.setText(text1,true);

            File file1 = new File("E:\\workspace\\ssmp_demo_02\\target\\学习笔记.rar");
            File file2 = new File("E:\\workspace\\ssmp_demo_02\\22022312542M617-0-lp.jpg");

            //添加附件包
            mimeMessageHelper.addAttachment(file1.getName(),file1);//获取文件的名称，定义附件名
            //添加附件图片
            mimeMessageHelper.addAttachment("需要耐心好好奋斗~",file2);//自定义附件名称

            //发送邮件
            javaMailSender.send(mimeMessage);
            log.info("邮件发送成功~");

        }catch (MessagingException ex){
            log.error("发送多部件邮件异常"+ex);
        }

    }
}
