package com.estoquemarquinho.marquinho.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.estoquemarquinho.marquinho.model.Product;
import com.estoquemarquinho.marquinho.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "editProduct";
        } else {
            return "redirect:/products";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        product.setId(id); // Certifique-se de que o ID é mantido ao atualizar
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }
}
