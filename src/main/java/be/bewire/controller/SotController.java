package be.bewire.controller;

import be.bewire.config.property.PersonProperties;
import be.bewire.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SotController {

    @Value("${message}")
    private String message;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PersonProperties personProperties;

    @RequestMapping(method = RequestMethod.GET, path = ("/message"))
    public String message() {
        return message;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/property")
    public String property() {
        return propertyService.getProperty();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/person")
    public String person() {
        return String.format("Hello %s %s!", personProperties.getFirstname(),
            personProperties.getLastname());
    }
}
