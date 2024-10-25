package com.mitocode.shop.application.port.in.cart;

import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.cart.NotEnoughItemsInStockException;
import com.mitocode.shop.model.customer.CustomerId;
import com.mitocode.shop.model.product.ProductId;

public interface AddToCartUseCase {

    Cart addToCart(CustomerId customerId, ProductId productId, int quantity)
        throws ProductNotFoundException,NotEnoughItemsInStockException;
}
