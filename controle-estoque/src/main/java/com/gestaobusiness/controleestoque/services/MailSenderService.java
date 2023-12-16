package com.gestaobusiness.controleestoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gestaobusiness.controleestoque.models.Mail;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class MailSenderService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Value("${jwt.secret}")
    private String jwtSecret;

    // @Autowired
    // private UsuarioService usuarioService;

    public void sendNewMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("{exemplo-email-a-colocar}@gmail.com");
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());
        mailSender.send(message);
    }
}