package be.bewire.health;

public enum ErrorCode {

    ERROR_5(5, "Property is disabled");

    private int code;
    private String description;

    ErrorCode(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
