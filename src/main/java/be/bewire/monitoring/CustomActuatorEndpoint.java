package be.bewire.monitoring;

import be.bewire.config.property.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.AbstractMvcEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class CustomActuatorEndpoint extends AbstractMvcEndpoint {

    @Autowired
    private PersonProperties personProperties;

    public CustomActuatorEndpoint() {
        super("/personprops", false);
    }

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public PersonProperties personProperties() {
        return personProperties;
    }
}
