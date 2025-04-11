package com.franquici.franqui.exception;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> details;
    
    // Constructor privado
    private ErrorResponse() {}

    // Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public List<String> getDetails() { return details; }
    
    // Método estático para iniciar el builder
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }
    
    // Clase interna Builder
    public static class ErrorResponseBuilder {
        private ErrorResponse response = new ErrorResponse();

        public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
            response.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder status(int status) {
            response.status = status;
            return this;
        }

        public ErrorResponseBuilder error(String error) {
            response.error = error;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            response.message = message;
            return this;
        }

        public ErrorResponseBuilder path(String path) {
            response.path = path;
            return this;
        }

        public ErrorResponseBuilder details(List<String> details) {
            response.details = details;
            return this;
        }

        public ErrorResponse build() {
            return response;
        }
    }
}


