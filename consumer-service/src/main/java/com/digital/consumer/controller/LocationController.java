package com.digital.consumer.controller;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.consumer.service.ConsumerLocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/consumer")
public class LocationController {

    private ConsumerLocationService consumerLocationService;

    @PostMapping("/near-shopper/locations")
    public ResponseEntity<NavigationDTO<LocationDTO>> getNearShopperLocationBySearchCriteria(@RequestBody CriteriaDTO criteriaDTO,
                                                                                             @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                             @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(consumerLocationService.getNearShopperLocationBySearchCriteria(criteriaDTO, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/near-shopper/{shopperId}/location/{locationId}/menus")
    public ResponseEntity<LocationDTO> getMenuByNearShopperAndLocation(@PathVariable("shopperId") Long shopperId, @PathVariable("locationId") Long locationId){
        return new ResponseEntity(consumerLocationService.getMenuByNearShopperAndLocation(shopperId, locationId), HttpStatus.OK);
    }
}
