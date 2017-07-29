package be.bewire.service;

public class DisabledPropertyServiceImpl implements PropertyService {

    @Override
    public String getProperty() {
        return "The property is disabled!";
    }
}
