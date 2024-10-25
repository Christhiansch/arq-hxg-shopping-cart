package com.mitocode.shop.application.port.in.product;

import com.mitocode.shop.model.product.Product;

import java.util.List;

public interface FindProductsUseCase {

    List<Product> findByNameOrDescription(String query);

}
