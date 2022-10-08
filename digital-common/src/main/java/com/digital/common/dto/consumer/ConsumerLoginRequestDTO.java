package com.digital.common.dto.consumer;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsumerLoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;
    private String password;

    //default constructor for JSON Parsing
    public ConsumerLoginRequestDTO() {
    }

    public ConsumerLoginRequestDTO(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

}