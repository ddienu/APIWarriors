package com.diegonunez.warriors.exception;

import com.diegonunez.warriors.common.ApiResponse;
import com.diegonunez.warriors.exception.Unchecked.EmptyListException;
import com.diegonunez.warriors.exception.Unchecked.EntityInUseException;
import com.diegonunez.warriors.exception.Unchecked.NoChangesMadeException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.hibernate.TransientObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> ilegalArgumentExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(
                new ApiResponse(
                        e.getMessage(),
                        "Error"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse(
                        e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                        "Error"
                )
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse> entityNotFoundExceptionHandler(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        e.getMessage(),
                        "Error"
                )
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse(
                        "Invalid request body",
                        "Error"
                )
        );
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ApiResponse> emptyListExceptionHandler(EmptyListException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse(
                        e.getMessage(),
                        "Error"
                )
        );
    }

    @ExceptionHandler(NoChangesMadeException.class)
    public ResponseEntity<ApiResponse> noChangesMadeExceptionHandler(NoChangesMadeException e){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        e.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<ApiResponse> entityInUseExceptionhandler(EntityInUseException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse(
                        e.getMessage(),
                        null
                )
        );
    }
}
