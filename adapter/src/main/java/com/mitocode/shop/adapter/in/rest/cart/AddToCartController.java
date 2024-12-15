package com.mitocode.shop.adapter.in.rest.cart;

import com.mitocode.shop.application.port.in.cart.AddToCartUseCase;
import com.mitocode.shop.application.port.in.cart.ProductNotFoundException;
import com.mitocode.shop.model.cart.Cart;
import com.mitocode.shop.model.cart.NotEnoughItemsInStockException;
import com.mitocode.shop.model.customer.CustomerId;
import com.mitocode.shop.model.product.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.mitocode.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;
import static com.mitocode.shop.adapter.in.rest.common.CustomerIdParser.parseCustomerId;
import static com.mitocode.shop.adapter.in.rest.common.ProductIdParser.parseProductId;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class AddToCartController {

    private final AddToCartUseCase addToCartUseCase;

    @PostMapping("/{customerId}/line-items")
    public CartWebModel addLineItem(
            @PathVariable("customerId") String customerIdString,
            @RequestParam("productId") String productIdString,
            @RequestParam("quantity") int quantity){
        CustomerId customerId = parseCustomerId(customerIdString);
        ProductId productId = parseProductId(productIdString);

        try{
            Cart cart = addToCartUseCase.addToCart(customerId, productId, quantity);
            return CartWebModel.fromDomainModel(cart);
        }catch (ProductNotFoundException e){
            throw clientErrorException(
                    HttpStatus.BAD_REQUEST, "The requested product does not exist");
        }catch (NotEnoughItemsInStockException e){
            throw clientErrorException(
                    HttpStatus.BAD_REQUEST, "Only %d items in stock".formatted(e.itemInStock()));
        }
    }
}
