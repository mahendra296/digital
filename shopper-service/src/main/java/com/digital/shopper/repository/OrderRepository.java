package com.digital.shopper.repository;

import com.digital.shopper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByConsumerIdAndId(Long consumerId, Long orderId);
}
