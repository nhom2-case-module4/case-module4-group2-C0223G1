package com.example.project_book.controller.order;

import com.example.project_book.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @GetMapping("")
    public String showOrder(Model model, Pageable pageable){
        model.addAttribute("orders",orderService.getOrders(pageable));
        return "orders/list";
    }
}
