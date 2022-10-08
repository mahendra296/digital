package com.digital.consumer.service;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.enums.CustomErrorCode;
import com.digital.common.exception.CustomException;
import com.digital.consumer.client.ShopperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ConsumerLocationService {

    @Autowired
    private ShopperClient shopperClient;

    public NavigationDTO<LocationDTO> getNearShopperLocationBySearchCriteria(CriteriaDTO criteriaDTO, PageRequest pageRequest) {
        try{
            return shopperClient.getNearShopperLocationBySearchCriteria(criteriaDTO, pageRequest.getPageNumber(), pageRequest.getPageSize());
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest shopper-location, Error: %s", e.getMessage()), CustomErrorCode.SHOPPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }

    public LocationDTO getMenuByNearShopperAndLocation(Long shopperId, Long locationId) {
        try{
            return shopperClient.getMenuByNearShopperAndLocation(shopperId, locationId);
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest shopper-location-menu details, Error: %s", e.getMessage()), CustomErrorCode.SHOPPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
