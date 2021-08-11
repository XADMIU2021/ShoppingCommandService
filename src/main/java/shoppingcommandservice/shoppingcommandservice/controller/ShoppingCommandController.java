package shoppingcommandservice.shoppingcommandservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import shoppingcommandservice.shoppingcommandservice.service.ShoppingService;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;

@RestController
public class ShoppingCommandController {
    @Autowired
    private ProductFeignClient productClient;

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/shopping-command/add-to-cart/{cartNumber}")
    public ResponseEntity<String> addToCart(@PathVariable String cartNumber, @RequestBody CartLineDTO dto, @RequestHeader(value="Customer-ID") String customerId) {
        // check stock from product service
        int quantity = 0;
        try {
            quantity = productClient.getStockAmount(dto.getProductNumber());
        } catch (Exception ex) {
            return new ResponseEntity<String>("Not enough product in stock", HttpStatus.OK);
        }
        if (quantity <= 0) return new ResponseEntity<String>("Not enough product in stock", HttpStatus.OK);

        // store event in database
        shoppingService.addToCart(cartNumber, dto, customerId);
        return new ResponseEntity<String>("Product added to cart successfully", HttpStatus.OK);
    }

    @FeignClient(name = "ProductService", fallback=Fallback.class)
    interface ProductFeignClient {
        @RequestMapping("/product/{productNumber}")
        public int getStockAmount(@PathVariable("productNumber") String productNumber);
    }

    class Fallback implements ProductFeignClient {
        @Override
        public int getStockAmount(String productNumber) {
            return 0;
        }
    }
}


