package com.digital.consumer.controller;

import com.digital.common.dto.consumer.ConsumerDTO;
import com.digital.common.dto.consumer.ConsumerLoginRequestDTO;
import com.digital.common.exception.RequestInvalidException;
import com.digital.consumer.Utils.CommonUtils;
import com.digital.consumer.config.JwtTokenUtil;
import com.digital.consumer.config.SecurityProperties;
import com.digital.consumer.model.Consumer;
import com.digital.consumer.model.ConsumerLoginResponse;
import com.digital.consumer.service.ConsumerService;
import com.digital.consumer.service.UserAuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    private JwtTokenUtil jwtTokenUtil;
    private UserAuthServiceImpl userAuthService;
    private ConsumerService consumerService;
    private SecurityProperties securityProperties;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody ConsumerLoginRequestDTO consumerLoginRequestDTO) throws Exception {

        userAuthService.authenticate(consumerLoginRequestDTO.getUserName(), consumerLoginRequestDTO.getPassword());

        final UserDetails userDetails = userAuthService.loadUserByUsername(consumerLoginRequestDTO.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Consumer user = userAuthService.findByEmail(consumerLoginRequestDTO.getUserName());
        return ResponseEntity.ok(new ConsumerLoginResponse(token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody ConsumerDTO consumerDTO) throws Exception {
        if (!CommonUtils.isEmailIsValid(consumerDTO.getEmail())) {
            throw new RequestInvalidException("Email is not valid.");
        }
        return ResponseEntity.ok(consumerService.registerUser(consumerDTO));
    }
}
