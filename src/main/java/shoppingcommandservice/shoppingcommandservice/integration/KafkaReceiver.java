package shoppingcommandservice.shoppingcommandservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import shoppingcommandservice.shoppingcommandservice.domain.CartCheckoutEvent;
import shoppingcommandservice.shoppingcommandservice.service.ShoppingService;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
    @Autowired
    private ShoppingService service;

    @KafkaListener(topics = {"cart-checkout"})
    public void receiveCheckout(@Payload String message) {
        System.out.println("Receiver received cart checkout message = "+ message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CartCheckoutEvent event = objectMapper.readValue(message, CartCheckoutEvent.class);
            service.handleCheckout(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
