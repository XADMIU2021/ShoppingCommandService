package shoppingcommandservice.shoppingcommandservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCommandController {
    @GetMapping("/shopping-command")
    public String defaultMethod() {
        return "shopping command";
    }
}
