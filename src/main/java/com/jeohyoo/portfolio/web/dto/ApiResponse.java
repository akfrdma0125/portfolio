package com.jeohyoo.portfolio.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ApiResponse<T> {
    @Setter
    private String path;
    private String code;
    private String message;
    private T data;
}
