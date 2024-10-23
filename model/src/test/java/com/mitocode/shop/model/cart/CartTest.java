package com.mitocode.shop.model.cart;

import com.mitocode.shop.model.product.Product;
import com.mitocode.shop.model.product.TestProductFactory;
import org.junit.jupiter.api.Test;

import static com.mitocode.shop.model.cart.TestCartFactory.emptyCartForRandomCustomer;
import static com.mitocode.shop.model.money.TestMoneyFactory.euros;
import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    @Test
    void givenEmptyCart_addTwoProducts_productsAreInCart() throws NotEnoughItemsInStockException{
        Cart cart = emptyCartForRandomCustomer();

        Product product1 = TestProductFactory.createTestProduct(euros(12, 99));
        Product product2 = TestProductFactory.createTestProduct(euros(5, 97));

        cart.addProduct(product1, 3);
        cart.addProduct(product2, 5);

        assertThat(cart.lineItems()).hasSize(2);
        assertThat(cart.lineItems().get(0).product()).isEqualTo(product1);
        assertThat(cart.lineItems().get(0).quantity()).isEqualTo(3);
        assertThat(cart.lineItems().get(1).product()).isEqualTo(product2);
        assertThat(cart.lineItems().get(1).quantity()).isEqualTo(5);
    }


}
