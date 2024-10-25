package com.mitocode.shop.application.port.in.cart;

import com.mitocode.shop.model.customer.CustomerId;
import com.mitocode.shop.model.cart.Cart;

public interface GetCartUseCase {

    Cart getCart(CustomerId customerId);
}
