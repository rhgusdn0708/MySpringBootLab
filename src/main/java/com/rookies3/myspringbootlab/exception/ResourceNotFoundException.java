package com.rookies3.myspringbootlab.exception; // ✅ 기존 exception 패키지

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
    }
}