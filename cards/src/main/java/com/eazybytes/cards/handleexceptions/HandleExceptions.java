package com.eazybytes.cards.handleexceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eazybytes.cards.dto.responses.ErrorResponse;
import com.eazybytes.cards.exceptions.CardAlreadyExistedException;
import com.eazybytes.cards.exceptions.CardNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardNotFoundException(CardNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CardAlreadyExistedException.class)
    public ResponseEntity<ErrorResponse> handleCardAlreadyExistedException(CardAlreadyExistedException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
