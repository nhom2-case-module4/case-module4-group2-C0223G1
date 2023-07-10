package com.example.project_book.service.order;

import javax.mail.MessagingException;

public interface IEmailServicee {
    void sendEmail(String toAddress, String subject, String message) throws MessagingException;
}
