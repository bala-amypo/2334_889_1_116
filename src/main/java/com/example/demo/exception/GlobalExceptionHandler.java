// package com.example.demo.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;

// @ControllerAdvice
// public class GlobalExceptionHandler {

//     // Handle Resource Not Found (404)
//     @ExceptionHandler(ResourceNotFoundException.class)
//     public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
//         Map<String, Object> body = new HashMap<>();
//         body.put("timestamp", LocalDateTime.now());
//         body.put("status", HttpStatus.NOT_FOUND.value());
//         body.put("error", "Not Found");
//         body.put("message", ex.getMessage());
//         return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//     }

//     // Handle Invalid Input (400)
//     @ExceptionHandler(InvalidInputException.class)
//     public ResponseEntity<Map<String, Object>> handleInvalidInput(InvalidInputException ex) {
//         Map<String, Object> body = new HashMap<>();
//         body.put("timestamp", LocalDateTime.now());
//         body.put("status", HttpStatus.BAD_REQUEST.value());
//         body.put("error", "Bad Request");
//         body.put("message", ex.getMessage());
//         return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//     }

//     // Handle Authentication failures (401)
//     @ExceptionHandler(AuthenticationException.class)
//     public ResponseEntity<Map<String, Object>> handleAuthException(AuthenticationException ex) {
//         Map<String, Object> body = new HashMap<>();
//         body.put("timestamp", LocalDateTime.now());
//         body.put("status", HttpStatus.UNAUTHORIZED.value());
//         body.put("error", "Unauthorized");
//         body.put("message", ex.getMessage());
//         return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
//     }

//     // Handle generic exceptions (500)
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
//         Map<String, Object> body = new HashMap<>();
//         body.put("timestamp", LocalDateTime.now());
//         body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//         body.put("error", "Internal Server Error");
//         body.put("message", ex.getMessage());
//         return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }
