package com.kbrsphere.shared.exception;

import com.kbrsphere.shared.enums.ApiStatus;
import com.kbrsphere.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handlePropertyNotFoundException(PropertyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(UnauthorizedPropertyAccessException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedPropertyAccessException(UnauthorizedPropertyAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(AccessDeniedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(ApiStatus.FAILED, "You do not have permission to perform this action", null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> requiredFields = ex.getBindingResult().getFieldErrors().stream().filter(error -> error.getCode() != null && (error.getCode().equals("NotBlank") || error.getCode().equals("NotNull") || error.getCode().equals("NotEmpty"))).map(FieldError::getField).distinct().toList();
        List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream().filter(error -> error.getCode() == null || (!error.getCode().equals("NotBlank") && !error.getCode().equals("NotNull") && !error.getCode().equals("NotEmpty"))).map(FieldError::getDefaultMessage).filter(Objects::nonNull).distinct().toList();

        String message;
        if (!requiredFields.isEmpty() && validationErrors.isEmpty()) {
            message = requiredFields.size() == 1 ? "Validation failed, " + requiredFields.get(0) + " is required" : "Validation failed, " + String.join(", ", requiredFields) + " are required";
        } else if (requiredFields.isEmpty() && !validationErrors.isEmpty()) {
            message = "Validation failed, " + String.join(", ", validationErrors);
        } else {
            message = "Validation failed, " + String.join(", ", requiredFields) + " are required, " + String.join(", ", validationErrors);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, message, null));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(UserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApiStatus.FAILED, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobal(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(ApiStatus.FAILED, "Something went wrong", null));
    }
}