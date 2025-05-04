package com.example.companyInventortyMgmt.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class RestResponseEntity {

    private final Status status;
    private final int statusCode;
    private final Message message;
    private final Object data;
    private final Object errors; 

    private RestResponseEntity(Status status, int statusCode, Message message, Object data, Object errors) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    // Static factory method for success responses
    public static ResponseEntity<Map<String, Object>> success(Object data, HttpStatus httpStatus, Message message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", Status.SUCCESS.getValue());
        body.put("statusCode", httpStatus.value());
        body.put("message", message);
        body.put("data", data);
        body.put("errors", null);

        return ResponseEntity.status(httpStatus).body(body);
    }

    // Static factory method for success responses
    public static ResponseEntity<Map<String, Object>> success(HttpStatus httpStatus, Message message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", Status.SUCCESS.getValue());
        body.put("statusCode", httpStatus.value());
        body.put("message", message);
        body.put("data", null);
        body.put("errors", null);

        return ResponseEntity.status(httpStatus).body(body);
    }

    // Static factory method for error responses
    public static ResponseEntity<Map<String, Object>> error(Object errors, HttpStatus httpStatus,Message message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", Status.ERROR.getValue());
        body.put("statusCode", httpStatus.value());
        body.put("message", message.getValue());
        body.put("data", null);
        body.put("errors", errors);

        return ResponseEntity.status(httpStatus).body(body);
    }

    public static ResponseEntity<Map<String, Object>> error(HttpStatus httpStatus,Message message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", Status.ERROR.getValue());
        body.put("statusCode", httpStatus.value());
        body.put("message", message.getValue());
        body.put("data", null);
        body.put("errors", null);

        return ResponseEntity.status(httpStatus).body(body);
    }

}
