package com.example.project_book.controller;

import com.example.project_book.model.*;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.cart.ICartService;
import com.example.project_book.service.home.IHomeService;
import com.example.project_book.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private IHomeService homeService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private ICartService cartService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }


    @GetMapping("/add/{id}/{num}")
    public String addCart(@SessionAttribute Cart cart, @PathVariable int id, @PathVariable int num,
                          RedirectAttributes redirectAttributes) {
        Product product = homeService.getBookById(id);
        if (product == null) {
            return "";
        } else {
            redirectAttributes.addFlashAttribute("check","add");
            cart.addItem(new Item(product, num));
            return "redirect:/welcome/view-all";
        }
    }

    @GetMapping("/show-cart")
    public String showCart(@SessionAttribute Cart cart, Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("order",new Order());
        return "user/cart";
    }

    @GetMapping("/plus/{id}")
    public String plusQuantityItem(@PathVariable int id, @SessionAttribute Cart cart, Model model,
                                   @ModelAttribute Order order, BindingResult bindingResult) {
        Product product = homeService.getBookById(id);
        Item item = new Item(product, 1);
        cart.addItem(item);
        model.addAttribute("cart", cart);
        return "user/cart";

    }

    @GetMapping("/minus/{id}")
    public String minusQuantityItem(@PathVariable int id, @SessionAttribute Cart cart, Model model,
                                    @ModelAttribute Order order, BindingResult bindingResult) {
        Product product = homeService.getBookById(id);
        if (cart.getAmountById(id) <= 1) {
            cart.removeItem(id);
        } else {
            Item item = new Item(product, -1);
            cart.addItem(item);
        }
        model.addAttribute("cart", cart);
        return "user/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id, @SessionAttribute Cart cart, Model model) {
        cart.removeItem(id);
        model.addAttribute("cart", cart);
        model.addAttribute("order", new Order());
        return "user/cart";
    }

    @PostMapping("")
    public String oderBook(@SessionAttribute Cart cart, @ModelAttribute Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "";
        }
        order.setDayOrder(LocalDate.now());
        order.setUser(usersService.findById(1));
        cartService.oderBook(cart,order);
        cart.clearCart();
        return "user/thank-you";

    }


}