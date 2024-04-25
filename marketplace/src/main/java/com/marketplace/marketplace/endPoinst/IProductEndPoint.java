package com.marketplace.marketplace.endPoinst;

public interface IProductEndPoint {
    String PRODUCT_BASE_URL = "/product";
    String PRODUCT_GET_ALL_URL = "/all";
    String PRODUCT_CREATE_URL = "create";
    String PRODUCT_UPDATE_URL = "/{id}";
    String PRODUCT_DEACTIVATE_URL = "/{id}";
    String PRODUCT_DELETE_URL = "/{id}";
    String PRODUCT_GET_URL = "/{id}";
   String PRODUCT_GET_BY_COLOR_URL = "";
}
