package com.enset.cassandra.service.impl;

import com.enset.cassandra.model.Product;
import com.enset.cassandra.repository.productRepository;
import com.enset.cassandra.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productServiceImpl implements productService {

    @Autowired
    private productRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(Product.builder().build());
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(product -> repository.delete(product));
    }
}