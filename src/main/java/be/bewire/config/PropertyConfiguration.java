package be.bewire.config;

import be.bewire.service.DisabledPropertyServiceImpl;
import be.bewire.service.EnabledPropertyServiceImpl;
import be.bewire.service.PropertyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "property", name = "enabled")
    public PropertyService enabledPropertyService() {
        return new EnabledPropertyServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(prefix = "property", name = "enabled",
        matchIfMissing = true, havingValue = "false")
    public PropertyService disabledPropertyService() {
        return new DisabledPropertyServiceImpl();
    }
}