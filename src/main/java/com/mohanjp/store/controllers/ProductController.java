package com.mohanjp.store.controllers;

import com.mohanjp.store.dto.ProductDto;
import com.mohanjp.store.entity.ProductEntity;
import com.mohanjp.store.mapper.ProductMapper;
import com.mohanjp.store.repository.CategoryRepository;
import com.mohanjp.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto request,
            UriComponentsBuilder uriBuilder
    ) {
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(request);
        product.setCategory(category);
        productRepository.save(product);

        var productDto = productMapper.toDto(product);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductDto request
    ) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        productMapper.update(request, product);
        product.setCategory(category);
        productRepository.save(product);
        request.setId(product.getId());

        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
