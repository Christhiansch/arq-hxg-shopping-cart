package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.application.port.in.cart.EmptyCartUseCase;
import com.mitocode.shop.model.customer.CustomerId;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class EmptyCartController {
    private final EmptyCartUseCase emptyCartUseCase;

    @DELETE
    @Path("{customerId}")
    public void deleteCart(@PathParam("customerId") String customerIdString){
        CustomerId customerId = parseCustomerId(customerIdString);
        emptyCartUseCase.emptyCart(customerId);
    }
}
