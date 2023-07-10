package com.example.project_book.service;

import com.example.project_book.service.order.IEmailServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class EmailServiceIpm implements IEmailServicee {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String recipient, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom("your-email@gmail.com");
        messageHelper.setTo(recipient);
        messageHelper.setSubject(subject);
        messageHelper.setText(message, true);

        mailSender.send(mimeMessage);
    }
}
