package com.digital.shopper.mockdata;

import com.digital.common.dto.NavigationDTO;
import com.digital.common.dto.consumer.CriteriaDTO;
import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.enums.OrderStatus;
import com.digital.shopper.model.Location;
import com.digital.shopper.model.Shopper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static OrderRequestDTO getOrderRequestDTO() {
        return OrderRequestDTO.builder()
                .consumerId(1L)
                .shopperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-10-18T15:20:40"))
                .build();
    }

    public static ConsumerOrderDTO getOrderDTO() {
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

    public static List<LocationDTO> getLocationDTOList() {
        return Arrays.asList(LocationDTO.builder()
                .shopperId(1L)
                .shopName("Rajeswary mall")
                .address1("Pragati nagar")
                .address2("near pragati road")
                .city("Ahmedabad")
                .postcode("384265")
                .country("INDIA")
                .contactNumber("+91 9922989593")
                .build());
    }

    public static CriteriaDTO getConsumerLocationDTO() {
        return CriteriaDTO.builder()
                .latitude(52.449942)
                .longitude(-0.9957452)
                .radius(5)
                .searchString("")
                .build();
    }

    public static Location getLocation() {
        Location location = Location.builder()
                .address1("city plaza")
                .address2("rani garden")
                .city("Patan")
                .postcode("123456")
                .country("INDIA")
                .contactNumber("+919822989891")
                .build();

        Shopper shopper = Shopper.builder().build();
        shopper.setId(1L);
        shopper.setShopName("Nescafe Hub");
        location.setId(1L);
        location.setShopper(shopper);
        location.setMenus(null);
        return location;
    }

    public static List<Location> getLocationList() {
        return Arrays.asList(getLocation());
    }
}
