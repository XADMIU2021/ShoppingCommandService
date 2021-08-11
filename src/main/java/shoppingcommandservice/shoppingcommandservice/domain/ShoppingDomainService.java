package shoppingcommandservice.shoppingcommandservice.domain;

import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;

@Service
public class ShoppingDomainService {
    public ShoppingCartEvent addToCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCartEvent cart = new ShoppingCartEvent(cartNumber, dto.getProductNumber(), dto.getQuantity(), customerId, "ProductAdded");
        return cart;
    }

    public ShoppingCartEvent updateFromCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCartEvent cart = new ShoppingCartEvent(cartNumber, dto.getProductNumber(), dto.getQuantity(), customerId, "ProductUpdated");
        return cart;
    }

    public ShoppingCartEvent removeFromCart(String cartNumber, String productNumber, String customerId) {
        return new ShoppingCartEvent(cartNumber, productNumber, 0, customerId, "ProductRemoved");
    }

    public CheckoutEvent checkoutCart(String customerId, String cartNumber) {
        return new CheckoutEvent(customerId, cartNumber);
    }
}
