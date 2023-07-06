package com.example.project_book.controller.order;

import com.example.project_book.model.Order;
import com.example.project_book.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String showOrder(Model model, Pageable pageable) {
        model.addAttribute("orders", orderService.findAllOrder(pageable));
        return "orders/list";
    }

    @GetMapping("{id}")
    public String deleteOrder(@PathVariable("id") int id) {
        Optional<Order> order = orderService.findByOrder(id);
        if (!order.isPresent() || order.get().isFlagDelete()) {
            return "/orders/error.404";
        } else {
            order.get().setFlagDelete(true);
            orderService.deleteOrder(order.get());
            return "redirect:/order";
        }
    }

    @GetMapping("/option/{option}")
    public String optionStatus(@PathVariable("option") String option) {
        String[] arrStr = option.split(",");
        System.out.println(Arrays.toString(arrStr));
        Optional<Order> order = orderService.findByOrder(Integer.parseInt(arrStr[1]));
        if (!order.isPresent() || order.get().isFlagDelete()) {
            return "/orders/error.404";
        } else {
            orderService.optionStatus(Integer.parseInt(arrStr[1]), Integer.parseInt(arrStr[0]));
            return "redirect:/order";
        }
    }
}
