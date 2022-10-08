package com.digital.shopper.model;

import com.digital.common.dto.shopper.ConsumerOrderItemDTO;
import com.digital.common.model.Audit;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends Audit {

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public static ConsumerOrderItemDTO build(OrderItem orderItem) {
        return ConsumerOrderItemDTO.builder()
                .id(orderItem.getId())
                .menuItemId(orderItem.getMenuItemId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static List<ConsumerOrderItemDTO> build(Set<OrderItem> orderItems) {
        if(CollectionUtils.isEmpty(orderItems) == false){
            return orderItems.stream().map(orderItem -> OrderItem.build(orderItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
