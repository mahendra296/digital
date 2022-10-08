package com.digital.consumer.controller;

import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.consumer.service.ConsumerOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/consumer")
public class OrderController {

    private ConsumerOrderService consumerOrderService;

    @PostMapping("/{consumerId}/order")
    public ResponseEntity<ConsumerOrderDTO> createOrder(@RequestBody OrderRequestDTO consumerOrder){
        return new ResponseEntity(consumerOrderService.createOrder(consumerOrder), HttpStatus.OK);
    }

    @GetMapping("/{consumerId}/order/{orderId}")
    public ResponseEntity<ConsumerOrderDTO> getOrderDetails(@PathVariable("consumerId") Long consumerId, @PathVariable("orderId") Long orderId){
        return new ResponseEntity(consumerOrderService.getOrderDetails(consumerId, orderId), HttpStatus.OK);
    }
}
