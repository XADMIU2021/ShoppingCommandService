package shoppingcommandservice.shoppingcommandservice.service;

import com.sun.tools.javac.comp.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.data.CheckoutEventRepository;
import shoppingcommandservice.shoppingcommandservice.data.ShoppingCartRepository;
import shoppingcommandservice.shoppingcommandservice.domain.*;
import shoppingcommandservice.shoppingcommandservice.integration.KafkaSender;
import shoppingcommandservice.shoppingcommandservice.service.dtos.CartLineDTO;

import java.util.List;

@Service
public class ShoppingService {
    @Autowired
    private ShoppingCartRepository repository;

    @Autowired
    private CheckoutEventRepository eventRepository;

    @Autowired
    private ShoppingAdapter adapter;

    @Autowired
    private ShoppingDomainService domainService;

    @Autowired
    private KafkaSender sender;

    public void addToCart(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCartEvent cart = domainService.addToCart(cartNumber, dto, customerId);
        repository.save(cart);

        // send cart as kafka event message
        ProductAddedEvent event = new ProductAddedEvent(cart.getCartNumber(), cart.getProductNumber(), cart.getQuantity(), cart.getCustomerId());
        sender.sendProductAdded("product-added", event);
    }

    public ShoppingCartEvent findByCartNumber(String cartNumber) {
        List<ShoppingCartEvent> carts = repository.findByCartNumber(cartNumber);
        if (carts == null || carts.size() == 0) return null;

        return carts.get(0);
    }

    public void removeFromCart(String cartNumber, String productNumber, String customerId) {
        ShoppingCartEvent cartEvent = domainService.removeFromCart(cartNumber, productNumber, customerId);
        repository.save(cartEvent);

        ProductRemovedEvent event = new ProductRemovedEvent(cartNumber, productNumber, customerId);
        sender.sendProductRemoved("product-removed", event);
    }

    public void updateProduct(String cartNumber, CartLineDTO dto, String customerId) {
        ShoppingCartEvent cart = domainService.updateFromCart(cartNumber, dto, customerId);
        repository.save(cart);

        // send cart as kafka event message
        ProductUpdatedEvent event = new ProductUpdatedEvent(cart.getCartNumber(), cart.getProductNumber(), cart.getQuantity(), cart.getCustomerId());
        sender.sendProductUpdated("product-updated", event);
    }

    public void handleCheckout(CartCheckoutEvent event) {
        CheckoutEvent checkoutEvent = new CheckoutEvent(event.getCustomerId(), event.getCartNumber());
        eventRepository.save(checkoutEvent);
    }
}
