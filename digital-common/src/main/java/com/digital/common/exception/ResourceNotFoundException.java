package com.digital.common.exception;

import com.digital.common.enums.CustomErrorCode;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private CustomErrorCode customErrorCode;

    public ResourceNotFoundException(final Class resourceClazz, final String resourceId) {
        super(format("Resource %s not found for %s", resourceClazz.getSimpleName(), resourceId));
    }

    public ResourceNotFoundException(final Class resourceClazz, final String resourceId, CustomErrorCode customErrorCode) {
        super(format("Resource %s not found for %s", resourceClazz.getSimpleName(), resourceId));
        this.customErrorCode = customErrorCode;
    }
}
