package com.example.project_book.controller;

import com.example.project_book.dto.dto_users.UsersDto;
import com.example.project_book.model.Cart;
import com.example.project_book.model.User;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.IUsersTypeService;
import com.example.project_book.service.home.IHomeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

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
                             RedirectAttributes redirectAttributes) {
        new UsersDto().validate(usersDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/login/register";
        }
        User users = new User();
        BeanUtils.copyProperties(usersDto, users);
        boolean check = usersService.existsByEmailUser(users.getEmailUser());
        if (check) {
            redirectAttributes.addFlashAttribute("msg", "Email already exists");
            return "/login/register";
        } else {
            this.usersService.add(users);
            redirectAttributes.addFlashAttribute("msg", "Register success");
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
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
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
    private String viewBlog(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            model.addAttribute("user", usersService.findByEmailUser(email));
        }
        return "user/blog";
    }

}
