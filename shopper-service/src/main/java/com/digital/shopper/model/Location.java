package com.digital.shopper.model;

import com.digital.common.dto.shopper.LocationDTO;
import com.digital.common.model.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="location")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends Audit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopper_id")
    private Shopper shopper;

    private String address1;

    private String address2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private Double latitude;

    private Double longitude;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "menu_location", joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "menu_id "))
    @ToString.Exclude
    @JsonIgnore
    private Set<Menu> menus = new HashSet<Menu>();

    public static LocationDTO locationModelToDTO(Location location, Boolean isRequiredMenu){
        return LocationDTO.builder()
                .id(location.getId())
                .shopperId(location.getShopper().getId())
                .shopName(location.getShopper().getShopName())

                .address1(location.getAddress1())
                .address2(location.getAddress2())
                .city(location.getCity())
                .postcode(location.getPostcode())
                .country(location.getCountry())
                .contactNumber(location.getContactNumber())
                .menus(isRequiredMenu == Boolean.TRUE ? Menu.build(location.getMenus()) : null)
                .build();
    }
    public static List<LocationDTO> locationModelToDTO(List<Location> locations){
        if(CollectionUtils.isEmpty(locations) == false){
            return locations.stream().map(location -> Location.locationModelToDTO(location, Boolean.FALSE)).collect(Collectors.toList());
        }
        return new ArrayList<LocationDTO>();
    }
}
