package shoppingcommandservice.shoppingcommandservice.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;
import shoppingcommandservice.shoppingcommandservice.service.dtos.ShoppingCartDTO;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, ShoppingCartDTO> kafkaTemplate;

    public void send(String topic, ShoppingCartDTO message){
        kafkaTemplate.send(topic, message);
    }
}
