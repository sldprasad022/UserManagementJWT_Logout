package com.techpixe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

 

      
//    @ExceptionHandler(CustomStatusException.class)
//    public ResponseEntity<Map<String, Object>> handleCustomStatusException(CustomStatusException ex) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("status",ex.getStatusCode());
//        response.put("error", "Custom Error");
//        response.put("message", ex.getMessage());
//
//        return ResponseEntity.status(ex.getStatusCode()).body(response);
//    }
    



    // Helper method to build a response entity
//    @ExceptionHandler(ResponseStatusException.class)
//    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", status.value());
//        response.put("error", status.getReasonPhrase());
//        response.put("message", message);
//
//        return new ResponseEntity<>(response, status);
//    }
	
	
	
	
	@ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        // Get the status and message from the exception object
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String message = ex.getReason();

        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);

        return new ResponseEntity<>(response, status);
    }
	
    

}





