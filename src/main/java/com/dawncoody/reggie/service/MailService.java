package com.dawncoody.reggie.service;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-24 20:03
 */
public interface MailService {
    public void sendEmail(String from, String to, String subject, String content);
}