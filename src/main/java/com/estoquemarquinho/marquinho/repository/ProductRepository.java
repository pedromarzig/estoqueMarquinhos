package com.estoquemarquinho.marquinho.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estoquemarquinho.marquinho.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}