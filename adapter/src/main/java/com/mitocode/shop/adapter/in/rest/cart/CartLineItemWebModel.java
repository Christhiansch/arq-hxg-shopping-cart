package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.model.cart.CartLineItem;
import com.mitocode.shop.model.money.Money;
import com.mitocode.shop.model.product.Product;
import com.mitocode.shop.model.product.ProductId;

public record CartLineItemWebModel(
        String productId, String productName, Money price, int quantity) {

    public static CartLineItemWebModel fromDomainModel(CartLineItem lineItem){
        Product product = lineItem.product();
        return new CartLineItemWebModel(
                product.id().value(), product.name(), product.price(), lineItem.quantity()
        );
    }
}
