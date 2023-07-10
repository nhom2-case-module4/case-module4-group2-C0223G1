package com.example.project_book.controller.user;

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
//    Create by: Tuan Vu
//    Day: 06/07/2023
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;
    @Autowired
    private IUsersTypeService usersTypeService;
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @GetMapping()
    public String getList(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("users", this.usersService.findAllByIsDeleteIsFalse(pageable));
        return "/list";
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @GetMapping("/form-add")
    public String showFormAdd(Model model) {
        model.addAttribute("add", new UsersDto());
        model.addAttribute("roleUser", this.usersTypeService.getListUsers());
        return "/form-add";
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute(name = "add") UsersDto usersDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        new UsersDto().validate(usersDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roleUser", this.usersTypeService.getListUsers());
            return "/form-add";
        }
        User users = new User();
        BeanUtils.copyProperties(usersDto, users);
        this.usersService.add(users);
        redirectAttributes.addFlashAttribute("msg", "successfully");
        return "redirect:/users";
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @GetMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (usersService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "not found");
        }
        usersService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "successfully");
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        if(usersService.findById(id)==null){
            redirectAttributes.addFlashAttribute("msg", "not found");
            return "redirect:/users";
        } else {
            model.addAttribute("edit", usersService.findById(id));
            model.addAttribute("roleUser", this.usersTypeService.getListUsers());
            return "/form-edit";
        }
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @PostMapping("/edit")
    public String edit(@ModelAttribute(name = "edit") UsersDto usersDto, RedirectAttributes redirectAttributes,Model model) {
        if (usersService.findById(usersDto.getIdUser()) == null) {
            model.addAttribute("roleUser", this.usersTypeService.getListUsers());
            redirectAttributes.addFlashAttribute("msg", "not found");
        } else {
            User users = new User();
            BeanUtils.copyProperties(usersDto, users);
            usersService.edit(users);
            redirectAttributes.addFlashAttribute("msg", "successfully");
        }
        return "redirect:/users";
    }
    //    Create by: Tuan Vu
    //    Day: 06/07/2023
    @GetMapping("/search")
    public String search(@RequestParam(name="name") String name, Pageable pageable, Model model) {
        Page<User> users = usersService.findOne(name, pageable);
        model.addAttribute("users", users);
        model.addAttribute("search",name);
        return"/list";
    }
}
