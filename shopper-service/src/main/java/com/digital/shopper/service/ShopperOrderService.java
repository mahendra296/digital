package com.digital.shopper.service;

import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.enums.CustomErrorCode;
import com.digital.common.enums.MenuStatus;
import com.digital.common.enums.OrderStatus;
import com.digital.common.enums.ShopperStatus;
import com.digital.common.exception.CustomException;
import com.digital.common.exception.ResourceNotFoundException;
import com.digital.shopper.model.MenuItem;
import com.digital.shopper.model.Order;
import com.digital.shopper.model.OrderItem;
import com.digital.shopper.model.Shopper;
import com.digital.shopper.repository.MenuItemRepository;
import com.digital.shopper.repository.OrderRepository;
import com.digital.shopper.repository.ShopperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopperOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public ConsumerOrderDTO createOrder(OrderRequestDTO orderRequest) {
        if(orderRequest.getSelectedMenuItemAndQuantityMap().size() == 0){
            log.error("An order has at least one item.");
            throw new CustomException("Order must have at least one item.", CustomErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        Shopper shopper = shopperRepository.findByIdAndStatus(orderRequest.getShopperId(), ShopperStatus.ACTIVE);
        if(shopper == null){
            log.error("Shopper not found with given id : {}", orderRequest.getShopperId());
            throw new ResourceNotFoundException(Shopper.class, String.format("Shopper not found with given id : %s", orderRequest.getShopperId()), CustomErrorCode.SHOPPER_NOT_FOUND);
        }

        Map<Long, BigDecimal> menuItemPriceMap = getShopperMenuItemPriceDetails(shopper.getId());

        checkValidation(orderRequest, shopper, menuItemPriceMap);

        Order order = mapToShopperOrder(orderRequest, shopper, menuItemPriceMap);
        orderRepository.save(order);
        log.info("Consumer order is placed successfully! consumer : {}, order : {}, order-status : {}", order.getConsumerId(), order.getId(), order.getStatus());

        return Order.build(order);
    }

    private void checkValidation(OrderRequestDTO consumerOrder, Shopper shopper, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
       List<Long> locationIds = shopper.getLocations().stream().map(location -> location.getId()).collect(Collectors.toList());
       if(!locationIds.contains(consumerOrder.getLocationId())){
           throw new CustomException(String.format("Shopper location not exist with location : %s", consumerOrder.getLocationId()), CustomErrorCode.SHOPPER_LOCATION_NOT_EXIST, HttpStatus.BAD_REQUEST);
       }

        for (Map.Entry<Long, Integer> menuItemAndQuantity : consumerOrder.getSelectedMenuItemAndQuantityMap().entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            if(menuItemPriceDetailsMap.get(menuItemId) == null){
                throw new CustomException(String.format("Shopper menu-item not exist with menu-item : %s, shopperId : %s", menuItemId, shopper.getId()), CustomErrorCode.SHOPPER_LOCATION_MENU_ITEM_NOT_EXIST, HttpStatus.BAD_REQUEST);
            }
        }
    }

    private Map<Long, BigDecimal> getShopperMenuItemPriceDetails(Long shopperId) {
        List<MenuItem> menuItems = menuItemRepository.findAllByMenuShopperIdAndMenuStatus(shopperId, MenuStatus.ACTIVE);
        Map<Long, BigDecimal> menuItemPriceMap = menuItems.stream().collect(Collectors.toMap(MenuItem::getId, MenuItem:: getPrice));
        return menuItemPriceMap;
    }

    private Order mapToShopperOrder(OrderRequestDTO consumerOrder, Shopper shopper, Map<Long, BigDecimal> menuItemPriceMap) {
        Order order = Order.builder()
                        .shopper(shopper)
                        .locationId(consumerOrder.getLocationId())
                        .menuId(consumerOrder.getMenuId())
                        .consumerId(consumerOrder.getConsumerId())
                        .status(OrderStatus.QUEUED)
                        .pickUpTime(consumerOrder.getPickUpTime())
                        .build();
        Set<OrderItem> orderItems = mapToOrderItems(consumerOrder.getSelectedMenuItemAndQuantityMap(), order, menuItemPriceMap);
        order.setOrderItems(orderItems);

        BigDecimal total = orderItems.stream().map(orderItem -> orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        order.setTotal(total);
        return order;
    }

    private Set<OrderItem> mapToOrderItems(Map<Long, Integer> selectedMenuItemAndQuantityMap, Order order, Map<Long, BigDecimal> menuItemPriceMap) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (Map.Entry<Long, Integer> menuItemAndQuantity : selectedMenuItemAndQuantityMap.entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            Integer quantity = menuItemAndQuantity.getValue();

            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItemAndQuantity.getKey())
                    .quantity(quantity)
                    .order(order)
                    .price(menuItemPriceMap.get(menuItemId) != null ? menuItemPriceMap.get(menuItemId) : BigDecimal.ZERO)
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public ConsumerOrderDTO getOrder(Long consumerId, Long orderId) {
        Order order = orderRepository.findByConsumerIdAndId(consumerId, orderId);
        if(order == null){
            throw new ResourceNotFoundException(Order.class, String.format("Customer-order not found with given customer: %s, order: %s", consumerId, orderId), CustomErrorCode.CONSUMER_ORDER_NOT_FOUND);
        }
        return Order.build(order);
    }
}
