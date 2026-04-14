package com.kbrsphere.user_management.exception;

import com.kbrsphere.user_management.dto.ApiResponse;
import com.kbrsphere.user_management.dto.ApiStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResourceFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(ApiStatus.FAILED, "Resource Not found", null));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(ApiStatus.FAILED, "Something went wrong", null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> missingFields = new ArrayList<>();
        List<String> otherErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();

            if (message != null && message.toLowerCase().contains("required")) {
                missingFields.add(field);
            } else {
                otherErrors.add(message);
            }
        });

        String finalMessage;

        if (!missingFields.isEmpty()) {
            // Handle required fields message
            String fields = String.join(", ", missingFields);

            finalMessage = missingFields.size() == 1
                    ? "Validation failed, " + fields + " is required"
                    : "Validation failed, " + fields + " are required";
        } else if (!otherErrors.isEmpty()) {
            // Handle other validations (email, size, etc.)
            finalMessage = "Validation failed, " + String.join(", ", otherErrors);
        } else {
            finalMessage = "Validation failed";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, finalMessage, null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleEnumError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(ApiStatus.FAILED,"Invalid role. Allowed values: ADMIN, USER, CUSTOMER",null));
    }
}