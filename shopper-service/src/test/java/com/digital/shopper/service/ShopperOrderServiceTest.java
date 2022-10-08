package com.digital.shopper.service;

import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.enums.ShopperStatus;
import com.digital.shopper.mockdata.TestUtils;
import com.digital.shopper.repository.OrderRepository;
import com.digital.shopper.repository.ShopperRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopperOrderServiceTest {

    @InjectMocks
    private ShopperOrderService shopperOrderService;

    @Mock
    private ShopperRepository shopperRepository;

    @Mock
    private OrderRepository orderRepository;

    /*@Test
    void getOrder() {
        ConsumerOrderDTO customerOrderDTO = TestUtils.getOrderDTO();
        when(shopperOrderService.getOrder(1L, 1L)).thenReturn(customerOrderDTO);
        ConsumerOrderDTO result = shopperOrderService.getOrder(1L, 1L);
        Assertions.assertEquals(customerOrderDTO, result);
    }

    @Test
    void createOrder() {
        OrderRequestDTO customerOrderRequestDTO = TestUtils.getOrderRequestDTO();
        ConsumerOrderDTO customerOrderDTO = TestUtils.getOrderDTO();
        when(shopperRepository.findByIdAndStatus(anyLong(), ShopperStatus.ACTIVE)).thenReturn(any());
        when(shopperOrderService.createOrder(customerOrderRequestDTO)).thenReturn(customerOrderDTO);
        ConsumerOrderDTO result =  shopperOrderService.createOrder(customerOrderRequestDTO);
        Assertions.assertEquals(customerOrderDTO, result);
    }*/
}