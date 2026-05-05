package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductRepository productRepository;

    public ShopController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Trang chủ");
        model.addAttribute("title", "Chào mừng đến với Cửa hàng Quần áo");
        model.addAttribute("subject", "Khám phá bộ sưu tập quần áo mới nhất của chúng tôi.");
        model.addAttribute("contentTemplate", "pages/home");
        model.addAttribute("contentFragment", "content");
        return "layout";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("pageTitle", "Danh sách sản phẩm");
        model.addAttribute("contentTemplate", "pages/list");
        model.addAttribute("contentFragment", "content");
        model.addAttribute("products", productRepository.findAll());
        return "layout";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("pageTitle", "Thêm sản phẩm mới");
        model.addAttribute("contentTemplate", "pages/create");
        model.addAttribute("contentFragment", "content");
        model.addAttribute("product", new Product());
        return "layout";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/shop/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        model.addAttribute("pageTitle", "Chỉnh sửa sản phẩm");
        model.addAttribute("contentTemplate", "pages/edit");
        model.addAttribute("contentFragment", "content");
        model.addAttribute("product", product);
        return "layout";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/shop/products";
    }

    @GetMapping("/products/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        model.addAttribute("pageTitle", "Xác nhận xóa sản phẩm");
        model.addAttribute("contentTemplate", "pages/delete-confirm");
        model.addAttribute("contentFragment", "content");
        model.addAttribute("product", product);
        return "layout";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/shop/products";
    }
}
