package com.techgo.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Extended error response format for validation errors.
 * Includes field-specific error messages.
 */
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    // Default constructor
    public ValidationErrorResponse() {}

    // Builder pattern support
    public static ValidationErrorResponseBuilder builder() {
        return new ValidationErrorResponseBuilder();
    }

    // Getters and Setters
    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * Builder class for ValidationErrorResponse
     */
    public static class ValidationErrorResponseBuilder {
        private final ValidationErrorResponse errorResponse;

        public ValidationErrorResponseBuilder() {
            errorResponse = new ValidationErrorResponse();
        }

        public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
            errorResponse.setTimestamp(timestamp);
            return this;
        }

        public ValidationErrorResponseBuilder status(int status) {
            errorResponse.setStatus(status);
            return this;
        }

        public ValidationErrorResponseBuilder error(String error) {
            errorResponse.setError(error);
            return this;
        }

        public ValidationErrorResponseBuilder message(String message) {
            errorResponse.setMessage(message);
            return this;
        }

        public ValidationErrorResponseBuilder errors(Map<String, String> errors) {
            errorResponse.setErrors(errors);
            return this;
        }

        public ValidationErrorResponseBuilder path(String path) {
            errorResponse.setPath(path);
            return this;
        }

        public ValidationErrorResponse build() {
            return errorResponse;
        }
    }
}