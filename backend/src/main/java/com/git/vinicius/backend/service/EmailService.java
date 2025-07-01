package com.git.vinicius.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.micrometer.observation.Observation.Context;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMail;

    public void emailTemplate(String para, String assunto, Context variaveisEmail, String arquivosTemplate) {
        String process = templateEngine.process(arquivosTemplate, variaveisEmail );
        MimeMessage message = javaMail.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(process, true);
        } catch (MessagingExeption e) {
            e.printStackTrace();
        }
        javaMail.send(message);
    }

    @Async
    public void enviarEmailSimples(String para, String assunto, String mensagem) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(para);
        simpleMail.setSubject(assunto);
        simpleMail.setText(mensagem);
        javaMail.send(simpleMail);
    }
}
