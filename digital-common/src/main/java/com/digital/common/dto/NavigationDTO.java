package com.digital.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavigationDTO<E> {
    private List<E> itemList;

    private long totalElements;

    private int totalPages;

    private int pageSize;

    private int pageNumber;
}
