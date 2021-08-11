package shoppingcommandservice.shoppingcommandservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCartEvent;
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
        if (quantity < dto.getQuantity()) return new ResponseEntity<String>("Not enough product in stock", HttpStatus.OK);

        // store event in database
        shoppingService.addToCart(cartNumber, dto, customerId);
        return new ResponseEntity<String>("Product added to cart successfully", HttpStatus.OK);
    }

    @PostMapping("/shopping-command/update-cart/{cartNumber}")
    public ResponseEntity<String> updateFromCart(@PathVariable String cartNumber, @RequestBody CartLineDTO dto, @RequestHeader(value="Customer-ID") String customerId) {
        // check stock from product service
        int quantity = 0;
        try {
            quantity = productClient.getStockAmount(dto.getProductNumber());
        } catch (Exception ex) {
            return new ResponseEntity<String>("Not enough product in stock", HttpStatus.OK);
        }
        if (quantity < dto.getQuantity()) return new ResponseEntity<String>("Not enough product in stock", HttpStatus.OK);

        // store event in database
        shoppingService.updateProduct(cartNumber, dto, customerId);
        return new ResponseEntity<String>("Product quantity updated on cart successfully", HttpStatus.OK);
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

    @DeleteMapping("/shopping-command/{cartNumber}/{productNumber}")
    public ResponseEntity<String> removeFromCart(@PathVariable String cartNumber, @PathVariable String productNumber, @RequestHeader(value="Customer-ID") String customerId) {
        ShoppingCartEvent cart = shoppingService.findByCartNumber(cartNumber);
        if (cart == null) return new ResponseEntity<String>("Cart no found", HttpStatus.NOT_FOUND);

        shoppingService.removeFromCart(cartNumber, productNumber, customerId);
        return new ResponseEntity<String>("Product removed from cart successfully", HttpStatus.OK);
    }
}


