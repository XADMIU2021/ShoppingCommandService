package shoppingcommandservice.shoppingcommandservice.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShoppingCartEvent {
    @Id
    private String id;

    private String cartNumber;
    private String productNumber;
    private int quantity;
    private String customerId;
    private String eventType;
    private String timeStamp;

    public ShoppingCartEvent(String cartNumber, String productNumber, int quantity, String customerId, String eventType) {
        this.cartNumber = cartNumber;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.customerId = customerId;
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
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

    public String getTimeStamp() {
        return timeStamp;
    }
}
