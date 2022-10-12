package com.k1687.leisure.grading.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private Integer status;
    private String error;
    private String message;
    private String detailedMessage;

}
