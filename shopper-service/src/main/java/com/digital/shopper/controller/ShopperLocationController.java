package com.digital.shopper.controller;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.shopper.service.ShopperLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopper")
public class ShopperLocationController {

    @Autowired
    private ShopperLocationService shopperLocationService;

    @PostMapping("/locations")
    public ResponseEntity<NavigationDTO<LocationDTO>> getShopperLocationAndMenuDetailsByCriteria(@RequestBody CriteriaDTO criteriaDTO,
                                                                                                 @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                                 @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(shopperLocationService.getShopperLocationAndMenuDetailsByCriteria(criteriaDTO, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/all-locations")
    public ResponseEntity<NavigationDTO<LocationDTO>> getAllShopperLocationAndMenuDetails(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                          @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(shopperLocationService.findAllLocation(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{shopperId}/location/{locationId}/menus")
    public ResponseEntity<LocationDTO> getShopperLocationMenuById(@PathVariable("shopperId") Long shopperId, @PathVariable("locationId") Long locationId){
        return new ResponseEntity(shopperLocationService.getShopperLocationMenuById(shopperId, locationId), HttpStatus.OK);
    }

    @GetMapping("/{shopperId}/location")
    public ResponseEntity<LocationDTO> getShopperLocationMenuByShopperId(@PathVariable("shopperId") Long shopperId){
        return new ResponseEntity(shopperLocationService.getShopperLocationMenuByShopperId(shopperId), HttpStatus.OK);
    }
}
