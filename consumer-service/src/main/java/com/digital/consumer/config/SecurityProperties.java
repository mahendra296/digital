package com.digital.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "security")
@Data
public class SecurityProperties {
    List<String> allowedPublicApis = new ArrayList<>();
}
