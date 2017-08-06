package be.bewire.monitoring;

import be.bewire.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private PropertyService propertyService;

    @Override
    public Health health() {
        ErrorCode errorCode = check();
        if (errorCode != null) {
            return Health.down().withDetail("Error Code", errorCode.getCode())
                .withDetail("Description", errorCode.getDescription()).build();
        }
        return Health.up().build();
    }

    private ErrorCode check() {
        if (propertyService.getProperty().contains("disabled")) {
            return ErrorCode.ERROR_5;
        }
        return null;
    }
}