package com.mitocode.shop.adapter.in.rest.common;

import com.mitocode.shop.adapter.in.rest.product.ProductInListWebModel;
import com.mitocode.shop.model.product.ProductId;
import org.springframework.http.HttpStatus;

import static com.mitocode.shop.adapter.in.rest.common.ControllerCommons.clientErrorException;

public class ProductIdParser {
    private ProductIdParser(){}

    public static ProductId parseProductId(String string){
        if(string == null){
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Missing 'productId'");
        }

        try{
            return new ProductId(string);
        }catch (IllegalArgumentException e){
            throw clientErrorException(HttpStatus.BAD_REQUEST, "Invalid 'productId'");
        }
    }

}
