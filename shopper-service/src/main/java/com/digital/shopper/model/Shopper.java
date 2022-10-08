package com.digital.shopper.model;

import com.digital.common.enums.ShopperStatus;
import com.digital.common.model.Audit;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopper")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shopper extends Audit {
    private String shopName;

    @Enumerated(EnumType.STRING)
    private ShopperStatus status = ShopperStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopper")
    @OrderBy("createdAt DESC")
    private Set<Location> locations = new HashSet<Location>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopper")
    @OrderBy("createdAt DESC")
    private Set<Menu> menus = new HashSet<Menu>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopper")
    @OrderBy("createdAt DESC")
    private Set<Order> orders = new HashSet<Order>();
}
