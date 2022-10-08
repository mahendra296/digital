package com.digital.shopper.repository;

import com.digital.common.enums.ShopperStatus;
import com.digital.shopper.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    Shopper findByIdAndStatus(Long shopperId, ShopperStatus active);
}
