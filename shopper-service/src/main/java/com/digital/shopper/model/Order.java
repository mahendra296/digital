package com.digital.shopper.model;

import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.enums.OrderStatus;
import com.digital.common.model.Audit;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Audit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    private Long locationId;

    private Long menuId;

    private Long consumerId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime pickUpTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public static ConsumerOrderDTO build(Order order) {
        return ConsumerOrderDTO.builder()
                .id(order.getId())
                .consumerId(order.getConsumerId())
                .shopperId(order.getShopper().getId())
                .locationId(order.getLocationId())
                .menuId(order.getMenuId())
                .totalAmount(order.getTotal())
                .orderStatus(order.getStatus())
                .orderItems(OrderItem.build(order.getOrderItems()))
                .pickUpTime(order.getPickUpTime())
                .build();
    }

    public static List<ConsumerOrderDTO> build(Set<Order> orders) {
        if(CollectionUtils.isEmpty(orders) == false){
            return orders.stream().map(order -> Order.build(order)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
