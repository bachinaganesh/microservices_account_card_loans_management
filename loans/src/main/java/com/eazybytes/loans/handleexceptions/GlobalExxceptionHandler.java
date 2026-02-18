package com.eazybytes.loans.handleexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eazybytes.loans.dtos.responses.ErrorResponse;
import com.eazybytes.loans.exceptions.LoanAlreadyExistedException;
import com.eazybytes.loans.exceptions.LoanNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExxceptionHandler {

    @ExceptionHandler(LoanAlreadyExistedException.class)
    public ResponseEntity<ErrorResponse> handleLoanAlreadyExistedException(LoanAlreadyExistedException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            request.getRequestURI(),
            ex.getMessage(),
            HttpStatus.CONFLICT.value(),
            java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            request.getRequestURI(),
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            request.getRequestURI(),
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            java.time.LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}