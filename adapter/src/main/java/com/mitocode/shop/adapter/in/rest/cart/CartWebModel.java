package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.money.Money;

import java.util.List;

public record CartWebModel(
        List<CartLineItemWebModel> lineItems, int numberOfItems, Money subtotal) {

    static CartWebModel fromDomainModel(Cart cart){
        return new CartWebModel(
                cart.lineItems().stream().map(CartLineItemWebModel::fromDomainModel).toList(),
                cart.numberOfItems(),
                cart.subTotal()
        );
    }
}
