package com.digital.common.dto.shopper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long id;

    private String category;

    private String subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;
}
