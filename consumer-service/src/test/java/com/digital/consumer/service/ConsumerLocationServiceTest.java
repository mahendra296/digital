package com.digital.consumer.service;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.consumer.client.ShopperClient;
import com.digital.consumer.mockdata.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerLocationServiceTest {

    @Mock
    private ShopperClient shopperClient;

    @InjectMocks
    private ConsumerLocationService consumerLocationService;

    @Test
    void getNearestShopperLocationBySearchCriteria() {
        NavigationDTO<LocationDTO> pageListDTO = TestUtils.getNavigationListLocationDTOs();

        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(shopperClient.getNearShopperLocationBySearchCriteria(TestUtils.getConsumerLocationDTO(),1,1)).thenReturn(pageListDTO);
        NavigationDTO<LocationDTO>  result =  consumerLocationService.getNearShopperLocationBySearchCriteria(TestUtils.getConsumerLocationDTO(),pageRequest);
        Assertions.assertEquals(1,result.getPageSize());
    }

    @Test
    void getMenuByNearestShopperAndLocation() {
        LocationDTO locationDTO = TestUtils.getLocationDTO();
        when(shopperClient.getMenuByNearShopperAndLocation(1L, 1L)).thenReturn(locationDTO);
        LocationDTO  result =  consumerLocationService.getMenuByNearShopperAndLocation(1L, 1L);
        Assertions.assertEquals(locationDTO, result);
    }
}