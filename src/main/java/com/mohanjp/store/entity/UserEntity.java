package com.mohanjp.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<AddressEntity> addresses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<TagEntity> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private ProfileEntity profile;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> wishlist = new HashSet<>();

    public void addAddress(AddressEntity address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(AddressEntity address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName) {
        var tag = new TagEntity(tagName);

        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(TagEntity tag) {
        tags.remove(tag);
        tag.getUsers().remove(this);
    }

    public void addFavoriteProduct(ProductEntity product) {
        wishlist.add(product);
    }
}
