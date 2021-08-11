package shoppingcommandservice.shoppingcommandservice.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckoutEvent {
    @Id
    private String id;
    private String customerId;
    private String cartNumber;
    private String timeStamp;

    public CheckoutEvent(String customerId, String cartNumber) {
        this.customerId = customerId;
        this.cartNumber = cartNumber;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
