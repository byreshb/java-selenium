package com.automation.shared;

public enum CsvColumn {
    DRIVER("driver"),
    URL("url"),
    CUSTOMER_FIRST_NAME("customerFirstName"),
    CUSTOMER_LAST_NAME("customerLastName"),
    CUSTOMER_POSTAL_CODE("customerPostalCode");

    private final String label;

    CsvColumn(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
