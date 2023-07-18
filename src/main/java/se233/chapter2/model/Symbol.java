package se233.chapter2.model;

public class Symbol {
    private String shortCode;
    private String description;

    public Symbol(String shortCode, String description) {
        this.shortCode = shortCode;
        this.description = description;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return shortCode + " - " + description ;
    }
}
