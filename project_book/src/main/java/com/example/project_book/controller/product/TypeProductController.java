package com.example.project_book.controller.product;

import com.example.project_book.dto.dto_product.TypeProductDto;
import com.example.project_book.dto.dto_users.RoleUserDto;
import com.example.project_book.model.RoleUser;
import com.example.project_book.model.TypeProduct;
import com.example.project_book.service.product.ITypeProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("typeProducts")
public class TypeProductController {

    @Autowired
    private ITypeProductService typeProductService;
    @GetMapping()
    public String getListUsersType(Model model){
        model.addAttribute("typeProductList",this.typeProductService.getListTypeProduct());
        return "typeProduct/list-typeProduct";
    }
    @GetMapping("/form-add-typeProduct")
    public String showFormAdd(Model model){
        model.addAttribute("add",new TypeProductDto());
        return "typeProduct/form-add-typeProduct";
    }
    @PostMapping("/add")
    public  String add(@ModelAttribute(name="add") TypeProductDto typeProductDto, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes){
       new TypeProductDto().validate(typeProductDto,bindingResult);
        if(bindingResult.hasErrors()){
            return "typeProduct/form-add-typeProduct";
        }
        TypeProduct typeProduct= new TypeProduct();
        BeanUtils.copyProperties(typeProductDto,typeProduct);
        this.typeProductService.add(typeProduct);
        redirectAttributes.addFlashAttribute("msg","Thêm thành công");
        return "redirect:/typeProducts";
    }
    @GetMapping("/delete/{id}")
    public String deleteTypeProduct(@PathVariable("id") int id) {
        typeProductService.delete(id);
        return "redirect:/typeProducts";
    }
}
