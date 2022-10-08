package com.digital.shopper.service;

import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.enums.ShopperStatus;
import com.digital.shopper.mockdata.TestUtils;
import com.digital.shopper.model.Location;
import com.digital.shopper.repository.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopperLocationServiceTest {

    @InjectMocks
    private ShopperLocationService shopperLocationService;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    public void setup() {

    }

    @Test
    void getShopperLocationMenuByShopperId() {
        List<LocationDTO> locationDTOList = TestUtils.getLocationDTOList();
        List<Location> locationList = TestUtils.getLocationList();
        when(locationRepository.findByShopperIdAndShopperStatus(1L, ShopperStatus.ACTIVE)).thenReturn(locationList);
        List<LocationDTO> result =  shopperLocationService.getShopperLocationMenuByShopperId(1L);
        Assertions.assertEquals(locationDTOList.size(), result.size());
    }
}