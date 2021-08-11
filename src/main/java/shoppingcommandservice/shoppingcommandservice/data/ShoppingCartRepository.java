package shoppingcommandservice.shoppingcommandservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCartEvent;

import java.util.List;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEvent, String> {
    List<ShoppingCartEvent> findByCartNumber(String cartNumber);
}
