package com.estoquemarquinho.marquinho.repository;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.estoquemarquinho.marquinho.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Product> findByNameContainingIgnoreCase(String name);
}