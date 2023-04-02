package com.dawncoody.reggie.service.impl;

import com.dawncoody.reggie.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-24 18:40
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    // public MailServiceImpl(JavaMailSender javaMailSender) {
    //     this.mailSender = javaMailSender;
    // }

    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
        log.info("邮箱发送成功");
    }

}
