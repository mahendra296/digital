package com.digital.common.dto.shopper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Long id;

    private Long shopperId;

    private String shopName;

    private String address1;

    private String address2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private List<MenuDTO> menus;
}
