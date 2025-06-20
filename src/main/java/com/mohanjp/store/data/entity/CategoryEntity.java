package com.mohanjp.store.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<ProductEntity> products = new HashSet<>();

    public CategoryEntity(String categoryName) {
        this.name = categoryName;
    }
}
