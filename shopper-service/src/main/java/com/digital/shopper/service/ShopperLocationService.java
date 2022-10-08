package com.digital.shopper.service;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.enums.CustomErrorCode;
import com.digital.common.enums.ShopperStatus;
import com.digital.common.exception.CustomException;
import com.digital.common.exception.ResourceNotFoundException;
import com.digital.shopper.model.Location;
import com.digital.shopper.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopperLocationService {

    @Autowired
    private LocationRepository locationRepository;

    public NavigationDTO<LocationDTO> getShopperLocationAndMenuDetailsByCriteria(CriteriaDTO criteriaDTO, PageRequest pageable){
        Page<Location> locationList = locationRepository.getShopperLocationMenuDetailsByCriteria(criteriaDTO.getLatitude(), criteriaDTO.getLongitude(), criteriaDTO.getRadius(), criteriaDTO.getSearchString(), pageable);

        return NavigationDTO.<LocationDTO>builder()
                .itemList(!CollectionUtils.isEmpty(locationList.getContent()) ? Location.locationModelToDTO(locationList.getContent()) : new ArrayList<>())
                .totalElements(locationList.getTotalElements())
                .pageNumber(locationList.getNumber())
                .pageSize(locationList.getSize())
                .totalPages(locationList.getTotalPages())
                .build();
    }

    public LocationDTO getShopperLocationMenuById(Long shopperId, Long locationId) {
        if(shopperId == null || locationId == null){
            throw new CustomException("shopperId and locationId is required.", CustomErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        Location location = locationRepository.findByIdAndShopperIdAndAndShopperStatus(locationId, shopperId, ShopperStatus.ACTIVE);

        if(location == null){
            throw new ResourceNotFoundException(Location.class,
                    String.format("Location not found for locationId: %s and shopperId: %s", locationId, shopperId),
                    CustomErrorCode.LOCATION_NOT_FOUND);
        }

        return Location.locationModelToDTO(location, Boolean.TRUE);
    }

    public List<LocationDTO> getShopperLocationMenuByShopperId(Long shopperId) {
        if(shopperId == null){
            throw new CustomException("shopperId is required.", CustomErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        List<Location> locationList = locationRepository.findByShopperIdAndShopperStatus(shopperId, ShopperStatus.ACTIVE);

        if(locationList == null){
            throw new ResourceNotFoundException(Location.class,
                    String.format("Location not found for shopperId: %s", shopperId),
                    CustomErrorCode.LOCATION_NOT_FOUND);
        }

        return locationList.stream().map(location -> Location.locationModelToDTO(location, Boolean.FALSE)).collect(Collectors.toList());
    }

    public NavigationDTO<LocationDTO> findAllLocation(PageRequest pageable){
        Page<Location> locationList = locationRepository.findAll(pageable);

        return NavigationDTO.<LocationDTO>builder()
                .itemList(!CollectionUtils.isEmpty(locationList.getContent()) ? Location.locationModelToDTO(locationList.getContent()) : new ArrayList<>())
                .totalElements(locationList.getTotalElements())
                .pageNumber(locationList.getNumber())
                .pageSize(locationList.getSize())
                .totalPages(locationList.getTotalPages())
                .build();
    }
}
