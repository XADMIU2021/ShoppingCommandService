package shoppingcommandservice.shoppingcommandservice.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.domain.ProductAddedEvent;
import shoppingcommandservice.shoppingcommandservice.domain.ProductRemovedEvent;
import shoppingcommandservice.shoppingcommandservice.domain.ProductUpdatedEvent;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;
import shoppingcommandservice.shoppingcommandservice.service.dtos.ShoppingCartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendProductAdded(String topic, ProductAddedEvent message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String resultAsString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, resultAsString);
        } catch(Exception ex) {}
    }

    public void sendProductUpdated(String topic, ProductUpdatedEvent message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String resultAsString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, resultAsString);
        } catch(Exception ex) {}
    }

    public void sendProductRemoved(String topic, ProductRemovedEvent message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String resultAsString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, resultAsString);
        } catch(Exception ex) {}
    }
}
