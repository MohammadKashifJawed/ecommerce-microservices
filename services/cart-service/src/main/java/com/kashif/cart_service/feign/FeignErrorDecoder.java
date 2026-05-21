package com.kashif.cart_service.feign;

import com.kashif.cart_service.exception.ProductNotExistException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 404)
            return new ProductNotExistException("Product with given id not found");
        else
            return new RuntimeException("Something bad happened");
    }
}
