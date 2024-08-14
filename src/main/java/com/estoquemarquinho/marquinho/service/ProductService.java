package com.estoquemarquinho.marquinho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoquemarquinho.marquinho.model.Product;
import com.estoquemarquinho.marquinho.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;
    
    public Product createProduct(Product product){
        return repo.save(product);
    }
    
    public List<Product> findAll(){
        return repo.findAll();
    }

    public Optional<Product> findById(String id) {
        return repo.findById(id);
    }
    
    public Product updateProduct(String id, Product updatedProduct) {
        // Busca o produto existente pelo Id
        Product existingProduct = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setDescription(updatedProduct.getDescription());
        
        return repo.save(existingProduct);
    }
    
    public void deleteProductById(String id) {
        Product product = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        repo.delete(product);
    }
    
    public List<Product> searProductsByname(String name){
    	return repo.findByNameContainingIgnoreCase(name);
    }
}
