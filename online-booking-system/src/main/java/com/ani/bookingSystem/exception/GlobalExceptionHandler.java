package com.ani.bookingSystem.exception;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(voilation -> errors.add(voilation.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorList = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        String message = String.join(", ", errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Validation failed: " + message);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmailException.class)
    public Map<String, String> handleDuplicateKeyException(DuplicateEmailException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("msg", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserExceptions(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("msg", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookingSlotNotFoundException.class)
    public Map<String, String> handleBookingSlotExceptions(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("msg", ex.getMessage());
        return errors;
    }
    
}