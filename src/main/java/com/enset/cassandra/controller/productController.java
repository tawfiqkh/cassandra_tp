package com.enset.cassandra.controller;


import com.enset.cassandra.model.Product;
import com.enset.cassandra.service.productService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "product JPA controller", description = "product CRUD API with documentation annotations")
public class productController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private productService productService;

    @Operation(summary = "Get all productes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "productes list",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {

        logger.info("*** Getting productes from DB ***");
        List<Product> list = productService.findAll();
        logger.info("productes fetched from DB :: {}", list);

        return ResponseEntity.ok().body(list);
    }




    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {

        logger.info("*** Getting product from DB for Id :: {}", id);
        Product product = productService.findById(id);

        if (StringUtils.isEmpty(product.getName()))
            return ResponseEntity.notFound().build();

        logger.info("product fetched :: {}", product);
        return ResponseEntity.ok().body(product);
    }




    @Operation(summary = "Create product ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save product",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Product> save(@Parameter(description = "product object to be created") @RequestBody Product product) {

        logger.info("*** Saving product to DB :: {}", product);
        Product savedproduct = productService.save(product);
        logger.info("*** Saved product to DB ***");

        return ResponseEntity.ok().body(savedproduct);
    }



    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update product",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping
    public ResponseEntity<Product> update(@Parameter(description = "product object to be updated") @RequestBody Product product) {

        logger.info("*** Updating product :: {}", product);
        Product updatedproduct = productService.update(product);
        logger.info("*** Updated product to DB :: {}", product);

        return ResponseEntity.ok().body(updatedproduct);
    }




    @Operation(summary = "Delete the product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the product",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@Parameter(description = "product id to be deleted") @PathVariable Long id) {

        logger.info("*** Deleting product from DB for Id :: {}", id);
        productService.delete(id);
        logger.info("*** Deleted product from DB for Id :: {}", id);

        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}