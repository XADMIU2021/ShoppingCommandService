package shoppingcommandservice.shoppingcommandservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcommandservice.shoppingcommandservice.domain.CheckoutEvent;

public interface CheckoutEventRepository extends MongoRepository<CheckoutEvent, String> {
}
