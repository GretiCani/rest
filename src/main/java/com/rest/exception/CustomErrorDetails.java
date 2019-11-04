package com.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CustomErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String errorDetails;
}
