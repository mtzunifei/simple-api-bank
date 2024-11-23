package com.example.simple.exception;

import org.junit.jupiter.api.Test;

import com.example.simple.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundException() {
        String resourceName = "User";
        String fieldName = "id";
        Object fieldValue = 1L;

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        assertEquals(resourceName, exception.getResourceName());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(fieldValue, exception.getFieldValue());
        assertEquals("User not found with id : '1'", exception.getMessage());
    }
}