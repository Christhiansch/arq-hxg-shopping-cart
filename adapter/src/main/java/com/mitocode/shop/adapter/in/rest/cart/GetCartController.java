package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.application.port.in.cart.GetCartUseCase;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.customer.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class GetCartController {
    private final GetCartUseCase getCartUseCase;

    @GetMapping("/{customerId}")
    public CartWebModel getCart(@PathVariable("customerId") String customerIdString){
        CustomerId customerId = parseCustomerId(customerIdString);
        Cart cart = getCartUseCase.getCart(customerId);
        return  CartWebModel.fromDomainModel(cart);
    }
}
