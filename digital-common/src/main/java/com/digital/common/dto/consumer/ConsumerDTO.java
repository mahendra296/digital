package com.digital.common.dto.consumer;

import com.digital.common.enums.AddressType;
import com.digital.common.enums.ConsumerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String address1;

    private String address2;

    private String city;

    private String postcode;

    private String email;

    private String password;

    private String country;

    private ConsumerStatus status;

    private AddressType addressType;
}
