package com.example.project_book.controller.product;

import com.example.project_book.dto.dto_product.ProductDto;

import com.example.project_book.model.Cart;
import com.example.project_book.model.Product;
import com.example.project_book.model.TypeProduct;

import com.example.project_book.model.User;
import com.example.project_book.service.IUsersService;
import com.example.project_book.service.product.IProductService;
import com.example.project_book.service.product.ITypeProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@SessionAttributes("cart")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ITypeProductService typeProductService;
    @Autowired
    private IUsersService usersService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping()
    public String getList(@PageableDefault(size = 4) Pageable pageable, Model model, HttpServletRequest request,
                          @RequestParam(name = "addedItemId", required = false) Long addedItemId) {
        Page<Product> productList = this.productService.findAllByIsDeleteIsFalse(pageable);
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            User user = usersService.findByEmailUser(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("productList", productList);
        return "/product/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("idDelete") int idDelete, RedirectAttributes redirectAttributes) {
        if (productService.findById(idDelete) == null) {
            redirectAttributes.addFlashAttribute("msg", "Id not found");
            return "redirect:/products";
        }
        productService.delete(idDelete);
        redirectAttributes.addFlashAttribute("msg", "Successful delete!");
        return "redirect:/products";
    }


    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (productService.findById(id) == null) {
            redirectAttributes.addFlashAttribute("msg", "Id not found");
            return "redirect:/products";
        }
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        model.addAttribute("edit", productDto);
        model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
        return "product/form-edit";
    }


    @PostMapping("/edit")
    public String processFormEdit(@ModelAttribute("edit") @Validated ProductDto productDto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
            model.addAttribute("msg", "New update not success!");
            return "product/form-edit";
        }
        Product product = productService.findById(productDto.getIdProduct());
        if (product == null) {
            redirectAttributes.addFlashAttribute("msg", "Id not found");
            return "redirect:/products";
        }
        BeanUtils.copyProperties(productDto, product);
        productService.edit(product);
        redirectAttributes.addFlashAttribute("msg", "Product updated successfully.");
        redirectAttributes.addFlashAttribute("editedItemId", productDto.getIdProduct()); // Thêm thông tin id của sản phẩm vừa sửa vào RedirectAttributes
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
            model.addAttribute("msg", "New addition not success!");
            model.addAttribute("typeProductList", this.typeProductService.getListTypeProduct());
            return "product/form-add";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        // Thêm đoạn code để lấy đối tượng TypeProduct từ cơ sở dữ liệu
        int idTypeProduct = productDto.getTypeProductDto().getIdTypeProduct();
        TypeProduct typeProduct = typeProductService.getTypeProductById(idTypeProduct);
        if (typeProduct == null) {
            redirectAttributes.addFlashAttribute("msg", "No product type found");
            return "redirect:/products";
        }
        product.setTypeProduct(typeProduct);

        this.productService.add(product);
        redirectAttributes.addFlashAttribute("msg", "New addition success!");
        redirectAttributes.addFlashAttribute("addedItemId", product.getIdProduct());

        // Lấy trang cuối cùng trong danh sách sản phẩm và redirect đến trang đó
        Pageable pageable = PageRequest.of(Integer.MAX_VALUE, 5);
        Page<Product> productList = this.productService.findAllByIsDeleteIsFalse(pageable);
        int lastPageNumber = productList.getTotalPages() - 1;
        return "redirect:/products?page=" + lastPageNumber;
    }


    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "author", required = false) String author,
                                 @PageableDefault(size = 5) Pageable pageable, HttpServletRequest request,
                                 Model model) {
        Page<Product> productList = productService.search(name, author, pageable);
        if (request.getUserPrincipal() == null) {
            model.addAttribute("check", "check");
        } else {
            String email = request.getUserPrincipal().getName();
            User user = usersService.findByEmailUser(email);
            model.addAttribute("user", user);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("search", name);
        model.addAttribute("author", author);
        return "/product/list";
    }


    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/form-detail";
    }

}
