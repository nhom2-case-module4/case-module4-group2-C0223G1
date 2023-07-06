package com.example.project_book.controller;

import com.example.project_book.dto.dto_users.UsersDto;
import com.example.project_book.model.User;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.IUsersTypeService;
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

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;
    @Autowired
    private IUsersTypeService usersTypeService;

    @GetMapping()
    public String getList(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("users", this.usersService.findAllByIsDeleteIsFalse(pageable));
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
        User users = new User();
        BeanUtils.copyProperties(usersDto, users);
        this.usersService.add(users);
        redirectAttributes.addFlashAttribute("msg", "thêm thành công");
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (usersService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "không tìm thấy id");
            return "redirect:/users";
        }
        usersService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "đã xoá thành công");
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if(usersService.findById(id)==null){
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
        if (usersService.findById(usersDto.getIdUser()) == null) {
            redirectAttributes.addFlashAttribute("msg", "sửa không thành công");
        } else {
            User users = new User();
            BeanUtils.copyProperties(usersDto, users);
            usersService.edit(users);
            redirectAttributes.addFlashAttribute("msg", "sửa thành công");
        }
        return "redirect:/users";
    }
    @GetMapping("/search")
    public String search(@RequestParam(name="name") String name, Pageable pageable, Model model) {
        Page<User> users = usersService.findOne(name, pageable);
        model.addAttribute("users", users);
        model.addAttribute("search",name);
        return"/list";
    }
}
