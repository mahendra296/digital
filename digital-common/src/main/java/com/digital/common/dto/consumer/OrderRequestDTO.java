package com.digital.common.dto.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Long consumerId;

    private Long shopperId;

    private Long locationId;

    private Long menuId;

    private LocalDateTime pickUpTime;

    private Map<Long, Integer> selectedMenuItemAndQuantityMap;
}
