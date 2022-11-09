package com.automation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    String customerFirstName;
    String customerLastName;
    String customerPostalCode;
}
