package shoppingcommandservice.shoppingcommandservice.service.dtos;

public class ShoppingCartDTO {
    private String cartNumber;
    private String productNumber;
    private int quantity;
    private String customerId;

    public ShoppingCartDTO(String cartNumber, String productNumber, int quantity, String customerId) {
        this.cartNumber = cartNumber;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.customerId = customerId;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
