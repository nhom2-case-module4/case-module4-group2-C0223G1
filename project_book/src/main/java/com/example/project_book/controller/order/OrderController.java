package com.example.project_book.controller.order;

import com.example.project_book.model.Cart;
import com.example.project_book.model.Order;
import com.example.project_book.projections.OrderDetailProjection;
import com.example.project_book.projections.OrderProjection;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String showList(Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        return "orders/list";
    }

    @Autowired
    private IUsersService usersService;

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

//    @GetMapping("/detail/{id}")
//    public String showDetail(@PathVariable("id") int id, Model model, Pageable pageable, HttpServletRequest request) {
//        List<OrderDetailProjection> orderDetailProjectionList = orderService.findDetail(id, pageable).getContent();
//        List<OrderProjection> orderProjections = orderService.findAllOrder(pageable).getContent();
//        double sum = 0;
//        String status = "";
//        String address = "";
//
//        for (OrderProjection item : orderProjections) {
//            if (item.getId() == id) {
//                status = item.getNameStatus();
//                address = item.getAddressPeople();
//                break;
//            }
//        }
//        for (OrderDetailProjection order : orderDetailProjectionList) {
//            sum += order.getPriceBook() * order.getNumberDetail();
//        }
//
//        model.addAttribute("address", address);
//        model.addAttribute("status", status);
//        model.addAttribute("sum", sum);
//        model.addAttribute("customer", orderDetailProjectionList.get(0).getNameUser());
//        model.addAttribute("details", orderService.findDetail(id, pageable));
//        return "orders/detail";
//    }


//    @PostMapping("/delete")
//    public String deleteOrder(@PathParam("id") int id, RedirectAttributes redirectAttributes) {
//        Optional<Order> order = orderService.findByOrder(id);
//        if (!order.isPresent() || order.get().isFlagDelete()) {
//            return "/orders/error.404";
//        } else {
//            redirectAttributes.addFlashAttribute("toast", "Delete Successful");
//            order.get().setFlagDelete(true);
//            orderService.deleteOrder(order.get());
//            return "redirect:/order";
//        }
//    }
//    @GetMapping("/list")
//    @ResponseBody
//    public ResponseEntity<?> getALL(Pageable pageable){
//        return new ResponseEntity<>(orderService.findAllOrder(pageable),HttpStatus.OK);
//    }

//    @GetMapping("")
//    public String showOrder(Model model, Pageable pageable) {
//        model.addAttribute("orders", orderService.findAllOrder(pageable));
//        return "orders/list";
//    }

    //    @PostMapping("{id}")
////    @CrossOrigin("*")
////    @ResponseBody
////    public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) {
////        Optional<Order> order = orderService.findByOrder(id);
////        if (!order.isPresent() || order.get().isFlagDelete()) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////        } else {
////            order.get().setFlagDelete(true);
////            orderService.deleteOrder(order.get());
////            return ResponseEntity.ok().build();
////        }
////    }

    //    @CrossOrigin("*")
//    @ResponseBody
//    @PostMapping  ("/delete/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable("id")int id) {
//        System.out.println(id);
//        Optional<Order> order = orderService.findByOrder(id);
//        if (!order.isPresent() || order.get().isFlagDelete()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            order.get().setFlagDelete(true);
//            orderService.deleteOrder(order.get());
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        }
//    }

//
//    @GetMapping("/option/{option}")
//    public ResponseEntity<?> optionStatus(@PathVariable("option") String option, Pageable pageable) {
//        String[] arrStr = option.split(",");
//        Optional<Order> order = orderService.findByOrder(Integer.parseInt(arrStr[1]));
//        List<OrderDetailProjection> orderDetailList = orderService.findDetail(Integer.parseInt(arrStr[1]), pageable).getContent();
//        if (!order.isPresent() || order.get().isFlagDelete()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            if (arrStr[0].equals("3")) {
//                if (order.get().getStatus().getIdStatus() == 4) {
//                    for (OrderDetailProjection a : orderDetailList) {
//                        orderService.giveProduct(a.getNumberDetail(), a.getIdProduct());
//                    }
//                }
//                order.get().setDayTake(LocalDate.now());
//                orderService.updateOrder(order.get());
//            } else if ((arrStr[0].equals("4"))) {
//                for (OrderDetailProjection a : orderDetailList) {
//                    orderService.returnProduct(a.getNumberDetail(), a.getIdProduct());
//                }
//                order.get().setDayTake(null);
//                orderService.updateOrder(order.get());
//            } else {
//                if (order.get().getStatus().getIdStatus() == 4) {
//                    for (OrderDetailProjection a : orderDetailList) {
//                        orderService.giveProduct(a.getNumberDetail(), a.getIdProduct());
//                    }
//                }
//                order.get().setDayTake(null);
//                orderService.updateOrder(order.get());
//            }
//            orderService.optionStatus(Integer.parseInt(arrStr[0]), Integer.parseInt(arrStr[1]));
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//    }

    //
//    @PostMapping("/search")
//    public String searchOrder(@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @RequestParam("select") int select, Pageable pageable, Model model,HttpServletRequest request) {
//        if (request.getUserPrincipal() == null) {
//            model.addAttribute("check", "check");
//        } else {
//            String email = request.getUserPrincipal().getName();
//            model.addAttribute("user", usersService.findByEmailUser(email));
//        }
//        if (dateEnd.equals("") && dateStart.equals("")) {
//            dateEnd = "2050-05-16";
//            dateStart = "2020-04-26";
//        }
//        if (!dateEnd.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$") || !dateStart.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$")) {
//            return "redirect:/order";
//        }
//        model.addAttribute("orders", orderService.searchAllOrder(dateStart, dateEnd, select, pageable));
//        return "orders/list";
//    }
//    @GetMapping("")
//    public String showList(Model model, Pageable pageable) {
//        Page<OrderProjection> orderList=orderService.findAllOrder(pageable);
//        model.addAttribute("orders", orderService.findAllOrder(pageable));
//        return "orders/list";
//    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") int id, Model model, Pageable pageable) {
        List<OrderDetailProjection> orderDetailProjectionList = orderService.findDetail(id, pageable).getContent();
        List<OrderProjection> orderProjections = orderService.findAllOrder(pageable).getContent();
        if (orderDetailProjectionList.isEmpty()){
            return "redirect:/order";
        }
        double sum = 0;
        String status = "";
        String note = "";
        for (OrderProjection item : orderProjections) {
            if (item.getId() == id) {
                status = item.getNameStatus();
                note = item.getNoteOrder();
                break;
            }
        }
        for (OrderDetailProjection order : orderDetailProjectionList) {
            sum += order.getPriceBook() * order.getNumberDetail();
        }
        model.addAttribute("note", note);
        model.addAttribute("status", status);
        model.addAttribute("sum", sum);
        model.addAttribute("customer", orderDetailProjectionList.get(0).getNameUser());
        model.addAttribute("details", orderService.findDetail(id, pageable));
        return "orders/detail";
    }

    //
//
//    @PostMapping  ("/delete")
//    public String deleteOrder(@PathParam("id") int id) {
//        System.out.println(id);
//        Optional<Order> order = orderService.findByOrder(id);
//        if (!order.isPresent() || order.get().isFlagDelete()) {
//            return "/orders/error.404";
//        } else {
//            order.get().setFlagDelete(true);
//            orderService.deleteOrder(order.get());
//            return "redirect:/order";
//        }
//    }
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<?> getALL(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(orderService.findAllOrder(pageable), HttpStatus.OK);
    }
    @GetMapping("/error")
    public String showError(){
        return "/orders/error.404";
    }

//    @GetMapping("")
//    public String showOrder(Model model, Pageable pageable) {
//        model.addAttribute("orders", orderService.findAllOrder(pageable));
//        return "orders/list";
//    }


    @CrossOrigin("*")
    @ResponseBody
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) {
        System.out.println(id);
        Optional<Order> order = orderService.findByOrder(id);
        if (!order.isPresent() || order.get().isFlagDelete()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            order.get().setFlagDelete(true);
            orderService.deleteOrder(order.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @CrossOrigin("*")
    @ResponseBody
    @GetMapping("/option/{option}")
    public ResponseEntity<?> optionStatus(@PathVariable("option") String option, Pageable pageable) {
        String[] arrStr = option.split(",");
        Optional<Order> order = orderService.findByOrder(Integer.parseInt(arrStr[1]));
        if (!order.isPresent() || order.get().isFlagDelete()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(order.get().getStatus().getIdStatus()==3||order.get().getStatus().getIdStatus()==4||order.get().getStatus().getIdStatus()==5){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        List<OrderDetailProjection> orderDetailList = orderService.findDetail(Integer.parseInt(arrStr[1]), pageable).getContent();
        if (arrStr[0].equals("5")) {
            for (OrderDetailProjection a : orderDetailList) {
                orderService.returnProduct(a.getNumberDetail(), a.getIdProduct());
            }
        }
        if(arrStr[0].equals("3")){
            order.get().setDayTake(LocalDate.now());
            orderService.updateOrder(order.get());
        }
        orderService.optionStatus(Integer.parseInt(arrStr[0]), Integer.parseInt(arrStr[1]));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //
//    @GetMapping("/option/{option}")
//    public String optionStatus(@PathVariable("option") String option,Pageable pageable) {
//        String[] arrStr = option.split(",");
//        Optional<Order> order = orderService.findByOrder(Integer.parseInt(arrStr[1]));
//        List<OrderDetailProjection>orderDetailList=orderService.findDetail(Integer.parseInt(arrStr[1]),pageable).getContent();
//        if (!order.isPresent() || order.get().isFlagDelete()) {
//            return "/orders/error.404";
//        } else {
//            if (arrStr[0].equals("3")) {
//                if(order.get().getStatus().getIdStatus()==4){
//                    for (OrderDetailProjection a: orderDetailList) {
//                        orderService.giveProduct(a.getNumberDetail(),a.getIdProduct());
//                    }
//                }
//                order.get().setDayTake(LocalDate.now());
//                orderService.updateOrder(order.get());
//            }else if((arrStr[0].equals("4"))){
//                for (OrderDetailProjection a: orderDetailList) {
//                   orderService.returnProduct(a.getNumberDetail(),a.getIdProduct());
//                }
//                order.get().setDayTake(null);
//                orderService.updateOrder(order.get());
//            } else {
//                if(order.get().getStatus().getIdStatus()==4){
//                    for (OrderDetailProjection a: orderDetailList) {
//                        orderService.giveProduct(a.getNumberDetail(),a.getIdProduct());
//                    }
//                }
//                order.get().setDayTake(null);
//                orderService.updateOrder(order.get());
//            }
//            orderService.optionStatus(Integer.parseInt(arrStr[0]), Integer.parseInt(arrStr[1]));
//            return "redirect:/order";
//        }
//    }
//
    @CrossOrigin("*")
    @ResponseBody
    @PostMapping("/search")
    public ResponseEntity<?> searchOrder(@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, @RequestParam("select") int select, Pageable pageable) {
        System.out.println(dateEnd);
        System.out.println(dateStart);
        System.out.println(select);
        if (dateEnd.equals("") && dateStart.equals("")&& select ==0) {
            return new ResponseEntity<>(orderService.findAllOrder(pageable), HttpStatus.OK);
        }
        if(dateEnd.equals("") && dateStart.equals("")){
            dateStart="2000-01-15";
            dateEnd="2050-01-15";
        }
        if (!dateEnd.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$") || !dateStart.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(orderService.searchAllOrder(dateStart, dateEnd, select, pageable).getContent().size());
        return new ResponseEntity<>(orderService.searchAllOrder(dateStart, dateEnd, select, pageable), HttpStatus.OK);
    }
}


