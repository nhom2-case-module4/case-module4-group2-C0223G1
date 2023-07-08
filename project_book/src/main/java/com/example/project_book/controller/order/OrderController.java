package com.example.project_book.controller.order;

import com.example.project_book.model.Order;
import com.example.project_book.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
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
            if (arrStr[0].equals("3")) {
                order.get().setDayTake(LocalDate.now());
                orderService.updateOrder(order.get());
            } else {
                order.get().setDayTake(null);
                orderService.updateOrder(order.get());
            }
            orderService.optionStatus(Integer.parseInt(arrStr[1]), Integer.parseInt(arrStr[0]));
            return "redirect:/order";
        }
    }

    @PostMapping("/search")
    public String searchOrder(@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @RequestParam("select") int select, Pageable pageable, Model model) {
        if (dateEnd.equals("") && dateStart.equals("")) {
            dateEnd= "2050-05-16";
            dateStart = "2020-04-26";
        }
        model.addAttribute("orders", orderService.searchAllOrder(dateStart, dateEnd, select, pageable));
        return "orders/list";
    }
}
