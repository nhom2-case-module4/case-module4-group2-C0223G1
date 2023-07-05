package com.example.bookstore.controller;

import com.example.bookstore.dto.UsersDto;
import com.example.bookstore.dto.UsersTypeDto;
import com.example.bookstore.model.Users;
import com.example.bookstore.model.UsersType;
import com.example.bookstore.repo.IUsersRepo;
import com.example.bookstore.service.IUsersService;
import com.example.bookstore.service.IUsersTypeService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private IUsersTypeService usersTypeService;

    @GetMapping()
    public String getList(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("users", this.usersService.findAllByFlagDeleteIsFalse(pageable));
        return "/list";
    }

    @GetMapping("/form-add")
    public String showFormAdd(Model model) {

        model.addAttribute("add", new UsersDto());
        model.addAttribute("userType", this.usersTypeService.getListUsers());
        return "/form-add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute(name = "add") UsersDto usersDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        new UsersDto().validate(usersDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/form-add";
        }
        Users users = new Users();
        BeanUtils.copyProperties(usersDto, users);
        this.usersService.add(users);
        redirectAttributes.addFlashAttribute("msg", "thêm thanh công");
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Users users, RedirectAttributes redirectAttributes) {
        if (usersService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "không tìm thấy id");
            return "redirect:/users";
        }
        usersService.delete(users);
        redirectAttributes.addFlashAttribute("msg", "đã xoá thành công");
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if (usersService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "không tìm thấy id");
            return "redirect:/users";
        } else {
            model.addAttribute("edit", usersService.findById(id));
            model.addAttribute("userType", this.usersTypeService.getListUsers());
            return "/form-edit";
        }

    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute(name = "edit") UsersDto usersDto, RedirectAttributes redirectAttributes) {
        if (usersService.findById(usersDto.getId()) == null) {
            redirectAttributes.addFlashAttribute("msg", "sửa không thành công");
        } else {
            Users users = new Users();
            BeanUtils.copyProperties(usersDto, users);
            usersService.edit(users);
            redirectAttributes.addFlashAttribute("msg", "sửa thành công");
        }
        return "redirect:/users";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name="name") String name, Pageable pageable, Model model) {
            Page<Users> users = usersService.findOne(name, pageable);
            model.addAttribute("users", users);
            model.addAttribute("search",name);
             return"/list";
        }

}
