package com.example.project_book.controller.product;

import com.example.project_book.dto.dto_product.ProductDto;
import com.example.project_book.dto.dto_product.TypeProductDto;
import com.example.project_book.dto.dto_users.UsersDto;
import com.example.project_book.model.Product;
import com.example.project_book.model.TypeProduct;
import com.example.project_book.model.User;
import com.example.project_book.service.product.IProductService;
import com.example.project_book.service.product.ITypeProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ITypeProductService typeProductService;

    @GetMapping()
    public String getList(@PageableDefault(size = 2) Pageable pageable, Model model) {
        model.addAttribute("productList", this.productService.findAllByIsDeleteIsFalse(pageable));
        return "/product/list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (productService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "không tìm thấy id");
            return "redirect:/products";
        }
        productService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "đã xoá thành công");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (productService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "không tìm thấy id");
            return "redirect:/products";
        }
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        model.addAttribute("edit", this.productService.findById(id));
        model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
        return "product/form-edit";
    }


    @PostMapping("/edit")
    public String edit(@Validated @ModelAttribute("edit") ProductDto productDto, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typeProductList", typeProductService.getListTypeProduct());
            return "product/form-edit";
        }
        Product product = productService.findById(productDto.getIdProduct());
        if (product == null) {
            redirectAttributes.addFlashAttribute("msg", "Tôi không tìm thấy sản phẩm để sửa");
            return "redirect:/products";
        }
        BeanUtils.copyProperties(productDto, product);
        if (productDto.getTypeProductDto() != null) {
            int idTypeProduct = productDto.getTypeProductDto().getIdTypeProduct();
            if (idTypeProduct > 0) {
                TypeProduct typeProduct = typeProductService.getTypeProductById(idTypeProduct);
                if (typeProduct != null) {
                    product.setTypeProduct(typeProduct);
                } else {
                    redirectAttributes.addFlashAttribute("msg", "Không tìm thấy loại sản phẩm");
                    return "redirect:/products";
                }
            }
        }
        productService.edit(product);
        redirectAttributes.addFlashAttribute("msg", "Tôi đã sửa sản phẩm thành công");

        return "redirect:/products";
    }


    @GetMapping("/form-add")
    public String showFormAdd(Model model) {
        model.addAttribute("add", new ProductDto());
        model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
        return "product/form-add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute(name = "add") ProductDto productDto, BindingResult bindingResult, Model model,
                          RedirectAttributes redirectAttributes) {
        new ProductDto().validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
            return "product/form-add";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        // Thêm đoạn code để lấy đối tượng TypeProduct từ cơ sở dữ liệu
        int idTypeProduct = productDto.getTypeProductDto().getIdTypeProduct();
        TypeProduct typeProduct = typeProductService.getTypeProductById(idTypeProduct);
        if (typeProduct == null) {
            redirectAttributes.addFlashAttribute("msg", "Không tìm thấy loại sản phẩm");
            return "redirect:/products";
        }
        product.setTypeProduct(typeProduct);

        this.productService.add(product);
        redirectAttributes.addFlashAttribute("msg", "thêm thành công");
        return "redirect:/products";
    }


    @GetMapping("/search")
    public String search(@RequestParam(name="name") String name, Pageable pageable, Model model) {
        Page<Product> products = productService.findOne(name, pageable);
        model.addAttribute("productList", products);
        return"/product/list";
    }
}
