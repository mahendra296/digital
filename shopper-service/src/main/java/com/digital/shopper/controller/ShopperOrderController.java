package com.digital.shopper.controller;

import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.shopper.service.ShopperOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopper")
public class ShopperOrderController {

    @Autowired
    private ShopperOrderService shopperOrderService;

    @PostMapping("/consumer-order")
    public ResponseEntity<ConsumerOrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequest){
        return new ResponseEntity(shopperOrderService.createOrder(orderRequest), HttpStatus.OK);
    }

    @GetMapping("/consumer-order/{consumerId}/order/{orderId}")
    public ResponseEntity<ConsumerOrderDTO> getOrder(@PathVariable("consumerId") Long consumerId, @PathVariable("orderId") Long orderId){
        return new ResponseEntity(shopperOrderService.getOrder(consumerId, orderId), HttpStatus.OK);
    }
}
