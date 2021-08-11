package shoppingcommandservice.shoppingcommandservice.service;

import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;
import shoppingcommandservice.shoppingcommandservice.service.dtos.ShoppingCartDTO;

@Service
public class ShoppingAdapter {
    public ShoppingCartDTO getDTOFromShoppingCart(ShoppingCart cart) {
        return new ShoppingCartDTO(cart.getCartNumber(), cart.getProductNumber(), cart.getQuantity(), cart.getCustomerId());
    }
}
