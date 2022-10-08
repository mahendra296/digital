package com.digital.shopper.repository;

import com.digital.common.enums.MenuStatus;
import com.digital.shopper.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByMenuShopperIdAndMenuStatus(Long shopperId, MenuStatus active);
}
