package com.digital.consumer.model;

import com.digital.common.dto.consumer.ConsumerDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConsumerLoginResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private ConsumerDTO consumer;

    public ConsumerLoginResponse(String token, Consumer user) {
        this.token = token;
        this.consumer = build(user);
    }

    public ConsumerDTO build(Consumer consumer) {
        ConsumerDTO consumerDTO = ConsumerDTO.builder()
                .id(consumer.getId())
                .firstName(consumer.getFirstName())
                .lastName(consumer.getLastName())
                .address1(consumer.getAddress1())
                .address2(consumer.getAddress2())
                .city(consumer.getCity())
                .postcode(consumer.getPostcode())
                .mobileNumber(consumer.getMobileNumber())
                .status(consumer.getStatus())
                .country(consumer.getCountry())
                .build();

        return consumerDTO;
    }
}