package com.mitocode.shop.application.service.cart;

import com.mitocode.shop.application.port.in.cart.GetCartUseCase;
import com.mitocode.shop.application.port.out.persistence.CartRepository;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.customer.CustomerId;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class GetCartService implements GetCartUseCase {

    private final CartRepository cartRepository;

    @Override
    public Cart getCart(CustomerId customerId){
        Objects.requireNonNull(customerId, "'customerId' mus not be null");

        return cartRepository
                .findByCustomerId(customerId)
                .orElseGet(() -> new Cart(customerId));
    }
}
