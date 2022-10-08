package com.digital.common.dto.shopper;

import com.digital.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerOrderDTO {
    private Long id;

    private Long consumerId;

    private Long shopperId;

    private Long locationId;

    private Long menuId;

    private BigDecimal totalAmount;

    private List<ConsumerOrderItemDTO> orderItems;

    private OrderStatus orderStatus;

    private LocalDateTime pickUpTime;
}
