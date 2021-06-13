package com.increpas.study.service;

import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

import com.increpas.study.vo.EmailVO;

@Component
public class EmailSender  {
     
    @Autowired
    protected JavaMailSender  mailSender;
 
    public void SendEmail(EmailVO email) throws Exception {
         
        MimeMessage msg = mailSender.createMimeMessage();
        msg.setSubject(email.getSubject());
        msg.setRecipient(RecipientType.TO , new InternetAddress(email.getReciver()));
        msg.setContent(email.getContent(), "text/html; charset=UTF8"); 
        mailSender.send(msg); 
    }
}