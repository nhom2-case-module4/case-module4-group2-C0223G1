package com.example.project_book.controller.login;

import com.example.project_book.dto.dto_users.UsersDto;
import com.example.project_book.model.User;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.IUsersTypeService;
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

@Controller
@RequestMapping("/home")
public class LoginController {
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
    //    Day: 06/07/2023
    @GetMapping("")
    public String showHome(Model model) {
//        model.addAttribute("list", homeService.getlistBook());
        return "user/index";
    }

    //    Create: Huynh Duc
    //    Day: 07/07/2023
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showFormLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return "redirect:/home";
//        }
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
    public String showFormRegisteredUser(Model model) {
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
            return "/form-add";
        }
        User users = new User();
        BeanUtils.copyProperties(usersDto, users);
        boolean check = usersService.existsByEmailUser(users.getEmailUser());
        if (check) {
            redirectAttributes.addFlashAttribute("msg", "Email already exists");
            return "/login/register";
        } else {
            this.usersService.add(users);
            redirectAttributes.addFlashAttribute("msg", "Đăng kí thành công");
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
}
