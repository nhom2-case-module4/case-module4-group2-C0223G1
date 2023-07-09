package com.example.project_book.controller.order;

import com.example.project_book.model.Order;
import com.example.project_book.model.User;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.order.IEmailServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private IEmailServicee emailService;
    @Autowired
    private IUsersService usersService;

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@ModelAttribute Order oder) throws MessagingException {

        String emailBody = "Thank you for your purchase!";
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=usersService.findByEmailUser(email);

        emailService.sendEmail(user.getEmailUser(), "Order Confirmation", emailBody);
        return ResponseEntity.ok().build();
    }
}
