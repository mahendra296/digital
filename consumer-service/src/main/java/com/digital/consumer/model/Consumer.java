package com.digital.consumer.model;

import com.digital.common.dto.consumer.ConsumerDTO;
import com.digital.common.enums.AddressType;
import com.digital.common.enums.ConsumerStatus;
import com.digital.common.model.Audit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "consumer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer extends Audit {
    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String address1;

    private String address2;

    private String city;

    private String postcode;

    private String country;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ConsumerStatus status = ConsumerStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public static ConsumerDTO consumerModelToDTO(Consumer consumer){
        return ConsumerDTO.builder()
                .id(consumer.getId())
                .firstName(consumer.getFirstName())
                .lastName(consumer.getLastName())
                .mobileNumber(consumer.getMobileNumber())
                .address1(consumer.getAddress1())
                .address2(consumer.getAddress2())
                .city(consumer.getCity())
                .postcode(consumer.getPostcode())
                .country(consumer.getCountry())
                .status(consumer.getStatus())
                .addressType(consumer.getAddressType())
                .build();
    }
}