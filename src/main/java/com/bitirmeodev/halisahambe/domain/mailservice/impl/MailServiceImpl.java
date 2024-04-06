package com.bitirmeodev.halisahambe.domain.mailservice.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailServiceImpl {
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private Integer port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    private static final String MAIL = "admin@buraksoft.com";


    public JavaMailSender getSMTPMailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }


    public void sendConfirmationMail(String to, String code) {
        MimeMessage message = getSMTPMailSender().createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom(MAIL);
            helper.setTo(to);
            helper.setSubject("Hesap Onay");
            helper.setText("Hesabı onaylamak için aşağıdaki bağlantıya tıklayınız \n" + code);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        getSMTPMailSender().send(message);
    }

    public void sendPasswordResetMail(String to, String code) {
        MimeMessage message = getSMTPMailSender().createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setTo(to);
            helper.setSubject("Şifre Değişikliği");
            helper.setText("Şifrenizi sıfırlamak için gerekli kodunuz: \n" + code);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        getSMTPMailSender().send(message);
    }
}
