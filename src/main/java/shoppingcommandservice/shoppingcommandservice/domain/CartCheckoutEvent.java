package shoppingcommandservice.shoppingcommandservice.domain;

public class CartCheckoutEvent {
    private String customerId;
    private String cartNumber;

    public CartCheckoutEvent() {
    }

    public CartCheckoutEvent(String customerId, String cartNumber) {
        this.customerId = customerId;
        this.cartNumber = cartNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCartNumber() {
        return cartNumber;
    }
}
