package shoppingcommandservice.shoppingcommandservice.service.dtos;

import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;

@Service
public class ShoppingAdapter {
    public ShoppingCart fromCartLineCustomerAndCartNumber(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCart cart = new ShoppingCart(cartNumber, dto.getProductNumber(), dto.getQuantity(), customerId);
        return cart;
    }

    public ShoppingCartDTO getDTOFromShoppingCart(ShoppingCart cart) {
        return new ShoppingCartDTO(cart.getCartNumber(), cart.getProductNumber(), cart.getQuantity(), cart.getCustomerId());
    }
}
