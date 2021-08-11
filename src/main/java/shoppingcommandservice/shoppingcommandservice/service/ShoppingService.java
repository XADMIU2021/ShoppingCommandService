package shoppingcommandservice.shoppingcommandservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.data.ShoppingCartRepository;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;
import shoppingcommandservice.shoppingcommandservice.integration.KafkaSender;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;
import shoppingcommandservice.shoppingcommandservice.service.dtos.ShoppingAdapter;
import shoppingcommandservice.shoppingcommandservice.service.dtos.ShoppingCartDTO;

@Service
public class ShoppingService {
    @Autowired
    private ShoppingCartRepository repository;

    @Autowired
    private ShoppingAdapter adapter;

    @Autowired
    private KafkaSender sender;

    public ShoppingCartDTO addToCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCart cart = adapter.fromCartLineCustomerAndCartNumber(cartNumber, dto, customerId);
        repository.save(cart);

        // send cart as kafka event message
        ShoppingCartDTO cartDTO = adapter.getDTOFromShoppingCart(cart);
        sender.send("product-added", cartDTO);

        return cartDTO;
    }
}
