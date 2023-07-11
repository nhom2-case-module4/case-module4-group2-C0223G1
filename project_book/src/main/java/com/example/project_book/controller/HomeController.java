package com.example.project_book.controller;

import com.example.project_book.dto.dto_users.UsersDto;
import com.example.project_book.model.*;
import com.example.project_book.repository.cart.ICartOrdeRepository;
import com.example.project_book.sercurity.WebUtils;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.IUsersTypeService;
import com.example.project_book.service.cart.CartService;
import com.example.project_book.service.cart.ICartService;
import com.example.project_book.service.home.IHomeService;
import com.example.project_book.service.order.IEmailServicee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes("cart")
@RequestMapping("/welcome")
public class HomeController {
    @Autowired
    private IHomeService homeService;

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @Autowired
    private IUsersService usersService;

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @Autowired
    private IUsersTypeService usersTypeService;

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @Autowired
    private HttpSession session;

    //    Create: Huynh Duc
    //    Day: 07/07/2023
    @Autowired
    private IEmailServicee emailService;

    @Autowired
    private ICartService cartService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showFormLogin(Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"anonymousUser".equals(authentication)) {
            return "redirect:/welcome";
        }
        return "/login/login";
    }

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.clearContext();
            redirectAttributes.addFlashAttribute("msg", "Đăng xuất thành công");
        }
        return "/login/login";
    }

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showFormRegisterAddUser(Model model) {
        model.addAttribute("add", new UsersDto());
        model.addAttribute("roleUser", this.usersTypeService.getListUsers());
        return "/login/register";
    }

    //    Create: Huynh Duc
    //    Day: 07/07/2023
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute(name = "add") UsersDto usersDto, BindingResult bindingResult,
                             Model model) throws MessagingException {
        new UsersDto().validate(usersDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/login/register";
        }
        User users = new User();
        BeanUtils.copyProperties(usersDto, users);
        boolean check = usersService.existsByEmailUser(users.getEmailUser());
        if (check) {
            model.addAttribute("email1", "Email already exists");
            return "/login/register";
        } else {
            this.usersService.add(users);
            String emailBody = "Hi "+users.getName()+ ", welcome to BookSaw!";
            emailService.sendEmail(users.getEmailUser(), "Register Success", emailBody);
            model.addAttribute("msg", check);
            return "/login/login";
        }
    }

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            model.addAttribute("msg", "Đăng xuất thành công");
        }
        return "/home/login";
    }

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    //    Create: Huynh Duc
//    Day: 06/07/2023
    @GetMapping("")
    public String showHome(Model model, HttpServletRequest request) {
        model.addAttribute("list", homeService.getlistBook());
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
            String nameBook = String.valueOf(homeService.getProductBestSellByName().getNameProduct());
            Product product = homeService.findBynameProductIs(nameBook);
            model.addAttribute("bestSell",product);
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
            String nameBook = String.valueOf(homeService.getProductBestSellByName().getNameProduct());
            Product product = homeService.findBynameProductIs(nameBook);
            model.addAttribute("bestSell",product);
        }
        return "user/index";
    }

    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @GetMapping("/view/{id}")
    public String showView(@PathVariable int id, Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        model.addAttribute("book", homeService.getBookById(id));
        return "user/single-product";
    }
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @GetMapping("/view-all")
    public String showAll(@SessionAttribute Cart cart, Model model, HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        model.addAttribute("cart", cart);
        model.addAttribute("list", homeService.getlistBook());
        model.addAttribute("list1", homeService.getBooksByType(1));
        model.addAttribute("list2", homeService.getBooksByType(2));
        model.addAttribute("list3", homeService.getBooksByType(3));
        model.addAttribute("list4", homeService.getBooksByType(4));
        model.addAttribute("list5", homeService.getBooksByType(5));
        return "user/shop";
    }
    //    Create: Huynh Duc
    //    Day: 06/07/2023
    @GetMapping("/view-blog")
    public String viewBlog(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        return "user/blog";
    }

    @GetMapping("/begin")
    public String goHome(@SessionAttribute Cart cart,HttpServletRequest request){
        String email = request.getUserPrincipal().getName();
        User user = usersService.findByEmailUser(email);
        List<CartOrder> list = cartService.getCartByIdUser(user.getIdUser());
        for (int i = 0; i < list.size(); i++) {
            Product product = homeService.getBookById(list.get(i).getIdProduct());
            int quantity = list.get(i).getQuantityProduct();
            Item item = new Item(product,quantity);
            cart.addItem(item);
        }
        return "redirect:/welcome";
    }

    @PostMapping("/search")
    public String searchBook(@RequestParam("search") String name, Model model,HttpServletRequest request,
                             @SessionAttribute Cart cart) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        model.addAttribute("cart", cart);
        model.addAttribute("list", homeService.searchProduct(name));
        model.addAttribute("list1", homeService.getBooksByType(1));
        model.addAttribute("list2", homeService.getBooksByType(2));
        model.addAttribute("list3", homeService.getBooksByType(3));
        model.addAttribute("list4", homeService.getBooksByType(4));
        model.addAttribute("list5", homeService.getBooksByType(5));
        return "user/shop";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "/login/403";
    }
}
