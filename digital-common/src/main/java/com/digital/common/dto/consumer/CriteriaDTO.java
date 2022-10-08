package com.digital.common.dto.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDTO {
    private Double latitude;
    private Double longitude;
    private Integer radius;
    private String searchString="";
}
