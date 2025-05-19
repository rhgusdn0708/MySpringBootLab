package com.rookies3.myspringbootlab.exception; // ✅ 기존 exception 패키지

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resource, String field, Object value) {
        super(String.format("%s already exists with %s: %s", resource, field, value));
    }
}