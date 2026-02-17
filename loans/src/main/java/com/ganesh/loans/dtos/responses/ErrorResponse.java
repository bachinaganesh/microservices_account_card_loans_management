package com.ganesh.loans.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private String path;
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
}
