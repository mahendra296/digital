package com.digital.shopper.repository;

import com.digital.common.enums.ShopperStatus;
import com.digital.shopper.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location lo WHERE lo.id IN (" +
            "SELECT l.id FROM location AS l JOIN shopper AS s ON (s.id = l.shopper_id)" +
            "JOIN menu_location AS ml ON (ml.location_id = l.id)" +
            "JOIN menu AS mu ON ( ml.menu_id = mu.id)" +
            "JOIN menu_item AS mi ON (mi.menu_id = mu.id)" +
            "WHERE ((?1 is null AND ?2 is null) OR (SQRT( POW(69.1 * (l.latitude - ?1), 2) + POW(69.1 * (?2 - l.longitude) * COS(l.latitude / 57.3), 2)) < ?3))" +
            "AND ((?4 is null) OR (s.shop_name like CONCAT('%',?4,'%') " +
            "OR mi.category like CONCAT('%',?4,'%') " +
            "OR (mi.sub_category like CONCAT('%',?4,'%') " +
            "OR mi.item_name like CONCAT('%',?4,'%'))))" +
            ")",nativeQuery = true)
    Page<Location> getShopperLocationMenuDetailsByCriteria(Double latitude, Double longitude, Integer radius, String searchString, PageRequest pageable);

    Location findByIdAndShopperIdAndAndShopperStatus(Long locationId, Long shopperId, ShopperStatus active);

    List<Location> findByShopperIdAndShopperStatus(Long shopperId, ShopperStatus active);
}
