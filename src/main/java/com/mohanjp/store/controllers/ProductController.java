package com.mohanjp.store.controllers;

import com.mohanjp.store.dto.ProductDto;
import com.mohanjp.store.entity.ProductEntity;
import com.mohanjp.store.mapper.ProductMapper;
import com.mohanjp.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "", name = "categoryId")
            Byte categoryId
    ) {
        List<ProductEntity> products;

        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Byte id) {
        var product = productRepository.findById(Long.valueOf(id)).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
