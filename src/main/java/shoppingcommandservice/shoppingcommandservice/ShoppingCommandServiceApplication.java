package shoppingcommandservice.shoppingcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableKafka
public class ShoppingCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCommandServiceApplication.class, args);
    }

}
