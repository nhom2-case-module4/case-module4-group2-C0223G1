package com.example.project_book.controller;

import com.example.project_book.service.home.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
//    @Autowired
//    private IHomeService homeService;
//
//    @GetMapping("")
//    public String showHome(Model model){
//        model.addAttribute("list", homeService.getlistBook());
//        return "user/index";
//    }
//
//    @GetMapping("/view/{id}")
//    public String showView(@PathVariable int id, Model model){
//        model.addAttribute("book",homeService.getBookById(id));
//        return "user/single-product";
//    }
//
//    @GetMapping("/view-all")
//    public String showAll(Model model){
//        model.addAttribute("list", homeService.getlistBook());
//        model.addAttribute("list1", homeService.getBooksByType(1));
//        model.addAttribute("list2", homeService.getBooksByType(2));
//        model.addAttribute("list3", homeService.getBooksByType(3));
//        model.addAttribute("list4", homeService.getBooksByType(4));
//        return "user/shop";
//    }
}
