package com.digital.consumer.mockdata;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestUtils {

    public static NavigationDTO<LocationDTO> getNavigationListLocationDTOs() {
        NavigationDTO<LocationDTO> navigationDTO = NavigationDTO.<LocationDTO>builder()
                .itemList(Collections.singletonList(LocationDTO.builder()
                        .id(1L)
                        .shopperId(1L)
                        .shopName("Rajeswary mall")
                        .address1("Pragati nagar")
                        .address2("near pragati road")
                        .city("Ahmedabad")
                        .postcode("384265")
                        .country("INDIA")
                        .contactNumber("+91 9922989593")
                        .build()))
                .pageNumber(1)
                .pageSize(1)
                .totalElements(1)
                .build();

        return navigationDTO;
    }

    public static OrderRequestDTO getCustomerOrderRequestDTO() {
        return OrderRequestDTO.builder()
                .consumerId(1L)
                .shopperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-10-18T15:20:40"))
                .build();
    }

    public static ConsumerOrderDTO getCustomerOrderDTO() {
        return ConsumerOrderDTO.builder()
                .id(1L)
                .consumerId(1L)
                .shopperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-10-18T15:20:40"))
                .orderStatus(OrderStatus.QUEUED)
                .build();
    }

    public static LocationDTO getLocationDTO() {
        return LocationDTO.builder()
                .shopperId(1L)
                .shopName("Rajeswary mall")
                .address1("Pragati nagar")
                .address2("near pragati road")
                .city("Ahmedabad")
                .postcode("384265")
                .country("INDIA")
                .contactNumber("+91 9922989593")
                .build();
    }

    public static CriteriaDTO getCustomerLocationDTO() {
        return CriteriaDTO.builder()
                .latitude(52.449942)
                .longitude(-0.9957452)
                .radius(5)
                .searchString("")
                .build();
    }
}
