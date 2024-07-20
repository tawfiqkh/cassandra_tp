package com.enset.cassandra.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Builder
@Table("product")
public class Product implements Serializable {

    @PrimaryKey
    private Long id;

    private String name;

    private int age;
}