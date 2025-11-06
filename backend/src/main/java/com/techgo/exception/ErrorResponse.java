package com.techgo.exception;

import java.time.LocalDateTime;

/**
 * Standard error response format for API errors.
 * Used across all endpoints for consistent error reporting.
 */
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Default constructor
    public ErrorResponse() {}

    // Builder pattern support
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Builder class for ErrorResponse
     */
    public static class ErrorResponseBuilder {
        private final ErrorResponse errorResponse;

        public ErrorResponseBuilder() {
            errorResponse = new ErrorResponse();
        }

        public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
            errorResponse.setTimestamp(timestamp);
            return this;
        }

        public ErrorResponseBuilder status(int status) {
            errorResponse.setStatus(status);
            return this;
        }

        public ErrorResponseBuilder error(String error) {
            errorResponse.setError(error);
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            errorResponse.setMessage(message);
            return this;
        }

        public ErrorResponseBuilder path(String path) {
            errorResponse.setPath(path);
            return this;
        }

        public ErrorResponse build() {
            return errorResponse;
        }
    }
}