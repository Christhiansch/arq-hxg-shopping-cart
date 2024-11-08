package com.mitocode.shop.bootstrap;

import com.mitocode.shop.adapter.in.rest.cart.AddToCartController;
import com.mitocode.shop.adapter.in.rest.cart.EmptyCartController;
import com.mitocode.shop.adapter.in.rest.cart.GetCartController;
import com.mitocode.shop.adapter.in.rest.product.FindProductsController;
import com.mitocode.shop.adapter.out.persistence.inmemory.InMemoryCartRepository;
import com.mitocode.shop.adapter.out.persistence.inmemory.InMemoryProductRepository;
import com.mitocode.shop.adapter.out.persistence.jpa.EntityManagerFactoryFactory;
import com.mitocode.shop.adapter.out.persistence.jpa.JpaCartRepository;
import com.mitocode.shop.adapter.out.persistence.jpa.JpaProductRepository;
import com.mitocode.shop.application.port.in.cart.AddToCartUseCase;
import com.mitocode.shop.application.port.in.cart.EmptyCartUseCase;
import com.mitocode.shop.application.port.in.cart.GetCartUseCase;
import com.mitocode.shop.application.port.in.product.FindProductsUseCase;
import com.mitocode.shop.application.port.out.persistence.CartRepository;
import com.mitocode.shop.application.port.out.persistence.ProductRepository;
import com.mitocode.shop.application.service.cart.AddToCartService;
import com.mitocode.shop.application.service.cart.EmptyCartService;
import com.mitocode.shop.application.service.cart.GetCartService;
import com.mitocode.shop.application.service.product.FindProductsService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.core.Application;

import java.util.Set;


public class RestEasyUndertowShopApplication extends Application {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Override
    public Set<Object> getSingletons(){
        initPersistenceAdapters();
        return Set.of(
                addToCartController(),
                getCartController(),
                emptyCartController(),
                findProductsController()
        );
    }

    private void initPersistenceAdapters(){
        String persistence = System.getProperty("persistence", "inmemory");
        switch (persistence){
            case "inmemory" -> initInMemoryAdapters();
            case "mysql" -> initMysqlAdapters();
            default -> throw new IllegalArgumentException(
                    "Invalid 'persistence' property"
            );
        }
    }

    private void initInMemoryAdapters(){
        cartRepository = new InMemoryCartRepository();
        productRepository = new InMemoryProductRepository();
    }

    private void initMysqlAdapters(){
        EntityManagerFactory entityManagerFactory =
                EntityManagerFactoryFactory.createMysqlEntityManagerFactory(
                        "jdbc:mysql://localhost:3306/shop", "root", "test"
                );

        cartRepository = new JpaCartRepository(entityManagerFactory);
        productRepository = new JpaProductRepository(entityManagerFactory);

    }

    private GetCartController getCartController(){
        GetCartUseCase getCartUseCase = new GetCartService(cartRepository);
        return new GetCartController(getCartUseCase);
    }

    private AddToCartController addToCartController(){
        AddToCartUseCase addToCartUseCase = new AddToCartService(cartRepository, productRepository);
        return new AddToCartController(addToCartUseCase);
    }

    private EmptyCartController emptyCartController(){
        EmptyCartUseCase emptyCartUseCase = new EmptyCartService(cartRepository);
        return new EmptyCartController(emptyCartUseCase);
    }

    private FindProductsController findProductsController(){
        FindProductsUseCase findProductsUseCase = new FindProductsService(productRepository);
        return new FindProductsController(findProductsUseCase);
    }

}
