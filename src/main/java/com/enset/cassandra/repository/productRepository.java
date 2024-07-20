package com.enset.cassandra.repository;

import com.enset.cassandra.model.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends CassandraRepository<Product, Long> {
}
