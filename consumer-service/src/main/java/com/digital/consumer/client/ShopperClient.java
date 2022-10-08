package com.digital.consumer.client;

import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.dto.shopper.LocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="shopper", url = "localhost:8081/api/v1/shopper")
public interface ShopperClient {

    @PostMapping("/locations")
    NavigationDTO<LocationDTO> getNearShopperLocationBySearchCriteria(@RequestBody CriteriaDTO criteriaDTO, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                      @RequestParam(value = "size", defaultValue = "20") int size);

    @GetMapping("/{shopperId}/location/{locationId}/menus")
    LocationDTO getMenuByNearShopperAndLocation(@PathVariable("shopperId") Long shopperId, @PathVariable("locationId") Long locationId);

    @PostMapping("/consumer-order")
    ConsumerOrderDTO createOrder(@RequestBody OrderRequestDTO consumerOrder);

    @GetMapping("/consumer-order/{consumerId}/order/{orderId}")
    ConsumerOrderDTO getOrderDetails(@PathVariable("consumerId") Long consumerId, @PathVariable("orderId") Long orderId);
}
