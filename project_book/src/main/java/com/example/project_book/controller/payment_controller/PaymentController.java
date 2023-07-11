package com.example.project_book.controller.payment_controller;

import com.example.project_book.config.PaymentConfig;
import com.example.project_book.model.*;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.cart.ICartService;
import com.example.project_book.service.home.IHomeService;
import com.example.project_book.service.order.IEmailServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IHomeService homeService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUsersService usersService;
    @Autowired
    private IEmailServicee emailService;


    @GetMapping("/create")
    public ModelAndView create(@SessionAttribute Cart cart) throws UnsupportedEncodingException {
        String orderType = "150000";
        int totalMoney = (int)cart.getTotalMoney();
        String amount = String.valueOf(totalMoney * 100 * 24000);
        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
//        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
        vnp_Params.put("vnp_Command", PaymentConfig.vnp_Command);


        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", "0:0:0:0:0:0:0:1");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
        return new ModelAndView("redirect:" + paymentUrl);
    }

    @GetMapping("/return")
    public String showReturn(@RequestParam String vnp_ResponseCode, Model model, @SessionAttribute Cart cart,
                             HttpServletRequest request, HttpSession session) throws MessagingException {
        String email = request.getUserPrincipal().getName();
        User user = usersService.findByEmailUser(email);
        cartService.deleteCartByIdUser(user.getIdUser());
        if (vnp_ResponseCode.equals("00")) {
            List<Item> list = cart.getItems();
//            for (int i = 0; i <list.size(); i++) {
//                Product product = list.get(i).getProduct();
//                product.setQuantityBooks(list.get(i).getProduct().getQuantityBooks()-list.get(i).getAmount());
//                homeService.update(product);
//            }
            String emailBody = "Thank you for your purchase!";
            emailService.sendEmail(user.getEmailUser(), "Order Confirmation", emailBody);
            Order order = (Order) session.getAttribute("order");
            cartService.oderBook(cart, order);
            cart.clearCart();
            model.addAttribute("user", user);
            model.addAttribute("cart", cart);
            return "user/thank-you";
        }
        List<Item> list = cart.getItems();
        for (int i = 0; i <list.size(); i++) {
            Product product = list.get(i).getProduct();
            product.setQuantityBooks(homeService.getBookById(list.get(i).getProduct().getIdProduct()).getQuantityBooks()+list.get(i).getAmount());
            homeService.update(product);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("order", new Order());
        return "user/cart";
    }
}