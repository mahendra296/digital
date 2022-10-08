package com.digital.consumer.service;

import com.digital.common.dto.consumer.ConsumerDTO;
import com.digital.common.dto.consumer.OrderRequestDTO;
import com.digital.common.dto.shopper.ConsumerOrderDTO;
import com.digital.common.enums.ConsumerStatus;
import com.digital.common.enums.CustomErrorCode;
import com.digital.common.exception.CustomException;
import com.digital.common.exception.RequestInvalidException;
import com.digital.consumer.client.ShopperClient;
import com.digital.consumer.model.Consumer;
import com.digital.consumer.repository.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    public ConsumerRepository consumerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Consumer registerUser(ConsumerDTO consumerDTO) throws RequestInvalidException {
        if (consumerDTO == null) throw new RequestInvalidException("Request is null or empty.");

        Boolean isUserExists = consumerRepository.existsByEmail(consumerDTO.getEmail());
        if (isUserExists) {
            throw new RequestInvalidException("User is already registered with email: " + consumerDTO.getEmail());
        }

        Consumer consumer = mapToConsumer(consumerDTO);
        return consumerRepository.save(consumer);
    }

    private Consumer mapToConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = Consumer.builder()
                .firstName(consumerDTO.getFirstName())
                .lastName(consumerDTO.getLastName())
                .address1(consumerDTO.getAddress1())
                .address2(consumerDTO.getAddress2())
                .city(consumerDTO.getCity())
                .email(consumerDTO.getEmail())
                .password(passwordEncoder.encode(consumerDTO.getPassword()))
                .postcode(consumerDTO.getPostcode())
                .mobileNumber(consumerDTO.getMobileNumber())
                .status(ConsumerStatus.ACTIVE)
                .addressType(consumerDTO.getAddressType())
                .country(consumerDTO.getCountry())
                .build();
        return consumer;
    }
}
