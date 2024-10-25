package com.mitocode.shop.application.port.out.persistence;

import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.customer.CustomerId;

import java.util.Optional;

public interface CartRepository {

    void save(Cart cart);

    Optional<Cart> findByCustomerId(CustomerId customerId);

    void deleteByCustomerId(CustomerId customerId);

}
