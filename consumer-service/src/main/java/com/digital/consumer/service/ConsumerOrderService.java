package com.digital.consumer.service;

import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.enums.CustomErrorCode;
import com.digital.common.exception.CustomException;
import com.digital.consumer.client.ShopperClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerOrderService {

    @Autowired
    public ShopperClient shopperClient;

    public ConsumerOrderDTO createOrder(OrderRequestDTO consumerOrder) {
        try{
            ConsumerOrderDTO consumerOrderDTO = shopperClient.createOrder(consumerOrder);
            log.info("Consumer order created successfully, consumerId : {}, orderId : {}, order-status : {}", consumerOrderDTO.getConsumerId(), consumerOrderDTO.getId(), consumerOrderDTO.getOrderStatus());
            return consumerOrderDTO;
        }catch (Exception e){
            log.error("Exception while create an order, Error : {}", e.getMessage());
            throw new CustomException(String.format("Exception while create an order, Error : %s", e.getMessage()), CustomErrorCode.SHOPPER_FEIGN_CLIENT_ERROR, HttpStatus.NO_CONTENT);
        }
    }

    public ConsumerOrderDTO getOrderDetails(Long consumerId, Long orderId) {
        try{
            return shopperClient.getOrderDetails(consumerId, orderId);
        }catch (Exception e){
            log.error("Exception while getting the order : consumer : {}, order : {}, Error : {}", consumerId, orderId, e.getMessage());
            throw new CustomException(String.format("Exception while getting the order : consumer : %s, order : %s, Error : %s", consumerId, orderId, e.getMessage()), CustomErrorCode.SHOPPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
