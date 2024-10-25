package com.mitocode.shop.application.port.in.cart;

import com.mitocode.shop.model.customer.CustomerId;

public interface EmptyCartUseCase {

    void emptyCart(CustomerId customerId);
}
