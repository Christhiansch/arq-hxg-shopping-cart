package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.application.port.in.cart.EmptyCartUseCase;
import com.mitocode.shop.model.cart.CartNotFoundException;
import com.mitocode.shop.model.customer.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mitocode.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;
import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class EmptyCartController {
    private final EmptyCartUseCase emptyCartUseCase;

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("customerId") String customerIdString){

        CustomerId customerId = parseCustomerId(customerIdString);

        emptyCartUseCase.emptyCart(customerId);
        return ResponseEntity.noContent().build();

    }
}
