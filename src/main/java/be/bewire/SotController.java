package be.bewire;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SotController {

    @RequestMapping(method = RequestMethod.GET)
    public String message() {
        return "Hello world!";
    }
}
