package com.enset.cassandra.service;


import com.enset.cassandra.model.Product;

import java.util.List;

public interface productService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Product product);

    void delete(Long id);

}