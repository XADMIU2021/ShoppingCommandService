package shoppingcommandservice.shoppingcommandservice.domain;

import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;

@Service
public class ShoppingDomainService {
    public ShoppingCart addToCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCart cart = new ShoppingCart(cartNumber, dto.getProductNumber(), dto.getQuantity(), customerId, "ProductAdded");
        return cart;
    }

    public ShoppingCart updateFromCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCart cart = new ShoppingCart(cartNumber, dto.getProductNumber(), dto.getQuantity(), customerId, "ProductUpdated");
        return cart;
    }

    public ShoppingCart removeFromCart(String cartNumber, String productNumber, String customerId) {
        return new ShoppingCart(cartNumber, productNumber, 0, customerId, "ProductRemoved");
    }
}
