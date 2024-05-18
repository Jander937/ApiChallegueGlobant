package com.marketplace.marketplace.endPoinst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IProductEndPointTest {
    @Test
    void testProductBaseUrl() {
        assertEquals("/product", IProductEndPoint.PRODUCT_BASE_URL);
    }

    @Test
    void testProductGetAllUrl() {
        assertEquals("/all", IProductEndPoint.PRODUCT_GET_ALL_URL);
    }

    @Test
    void testProductCreateUrl() {
        assertEquals("create", IProductEndPoint.PRODUCT_CREATE_URL);
    }

    @Test
    void testProductUpdateUrl() {
        assertEquals("/{id}", IProductEndPoint.PRODUCT_UPDATE_URL);
    }

    @Test
    void testProductDeactivateUrl() {
        assertEquals("/{id}", IProductEndPoint.PRODUCT_DEACTIVATE_URL);
    }

    @Test
    void testProductDeleteUrl() {
        assertEquals("/{id}", IProductEndPoint.PRODUCT_DELETE_URL);
    }

    @Test
    void testProductGetUrl() {
        assertEquals("/{id}", IProductEndPoint.PRODUCT_GET_URL);
    }

    @Test
    void testProductGetByColorUrl() {
        assertEquals("", IProductEndPoint.PRODUCT_GET_BY_COLOR_URL);
    }
}