package com.example.project_book.controller;

import com.example.project_book.model.Cart;
import com.example.project_book.model.Item;
import com.example.project_book.model.Product;
import com.example.project_book.service.home.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    IHomeService homeService;

//    @ModelAttribute("cart")
//    public Cart setupCart() {
//        return new Cart();
//    }

    @GetMapping("/add/{id}/{num}")
    public String addCart(@SessionAttribute Cart cart, @PathVariable int id, @PathVariable int num) {
        Product product = homeService.getBookById(id);
        if (product == null) {
            return "";
        } else {
            cart.addItem(new Item(product,num));
            return "redirect:/home/view-all";
        }
    }


}