package com.marketplace.marketplace.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTests {
    @Test
    void testNotFoundException() {
        String message = "Resource not found";
        NotFoundException exception = new NotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductAttributesFormatException() {
        String message = "Invalid product attributes format";
        ProductAttributesFormatException exception = new ProductAttributesFormatException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductEmptyException() {
        String message = "Product details are empty";
        ProductEmptyException exception = new ProductEmptyException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductEqualException() {
        String message = "Products are identical";
        ProductEqualException exception = new ProductEqualException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductExistException() {
        String message = "Product already exists";
        ProductExistException exception = new ProductExistException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductIdExistException() {
        String message = "Product ID already exists";
        ProductIdExistException exception = new ProductIdExistException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testProductNotFoundException() {
        String message = "Product not found";
        ProductNotFoundException exception = new ProductNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
