package com.digital.shopper.model;

import com.digital.common.dto.shopper.MenuItemDTO;
import com.digital.common.model.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="menu_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends Audit {

    private String category;

    private String subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public static MenuItemDTO build(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .category(menuItem.getCategory())
                .subCategory(menuItem.getSubCategory())
                .itemName(menuItem.getItemName())
                .price(menuItem.getPrice())
                .quantity(menuItem.getQuantity())
                .build();
    }

    public static List<MenuItemDTO> build(Set<MenuItem> menuItems) {
        if(CollectionUtils.isEmpty(menuItems) == false){
            return menuItems.stream().map(menuItem -> MenuItem.build(menuItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
