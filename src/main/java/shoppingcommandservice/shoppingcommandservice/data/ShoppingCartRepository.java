package shoppingcommandservice.shoppingcommandservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcommandservice.shoppingcommandservice.domain.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByCartNumber(String cartNumber);
}
