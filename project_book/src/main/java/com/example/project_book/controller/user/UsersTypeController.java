package com.example.project_book.controller.user;

import com.example.project_book.dto.UsersTypeDto;
import com.example.project_book.model.UsersType;
import com.example.project_book.service.IUsersTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usersType")
public class UsersTypeController {
    @Autowired
    private IUsersTypeService usersTypeService;
    @GetMapping()
    public String getListUsersType(Model model){
        model.addAttribute("users",this.usersTypeService.getListUsers());
        return "/list-userType";
    }
    @GetMapping("/form-add-userType")
    public String showFormAdd(Model model){
        model.addAttribute("add",new UsersTypeDto());
        return "/form-add-userType";
    }
    @PostMapping("/add")
    public  String add(@ModelAttribute(name="add") UsersTypeDto usersTypeDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/form-add-userType";
        }
        UsersType usersType= new UsersType();
        BeanUtils.copyProperties(usersType,usersTypeDto);
        this.usersTypeService.add(usersType);
        redirectAttributes.addFlashAttribute("msg","Thêm thành công");
        return "redirect:/usersType";
    }
}
