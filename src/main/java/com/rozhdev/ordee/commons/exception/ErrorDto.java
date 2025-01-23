package com.rozhdev.ordee.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private Status status;
    private Integer code;
    private String message;
    private List<ErrorDetail> errors = new ArrayList<>();
}
