package shoppingcommandservice.shoppingcommandservice.domain;

import org.springframework.data.annotation.Id;

public class ShoppingCart {
    @Id
    private String id;

    private String cartNumber;
    private String productNumber;
    private int quantity;
    private String customerId;
    private String eventType;

    public ShoppingCart(String cartNumber, String productNumber, int quantity, String customerId, String eventType) {
        this.cartNumber = cartNumber;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.customerId = customerId;
        this.eventType = eventType;
    }

    public String getId() {
        return id;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEventType() {
        return eventType;
    }
}
