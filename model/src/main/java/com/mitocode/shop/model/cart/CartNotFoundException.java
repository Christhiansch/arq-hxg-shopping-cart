package com.mitocode.shop.model.cart;

public class CartNotFoundException extends Exception{

    public CartNotFoundException(String message){ super(message); }

    public CartNotFoundException(String message, Throwable cause){ super(message, cause);}

}


