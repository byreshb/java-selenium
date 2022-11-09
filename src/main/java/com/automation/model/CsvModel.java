package com.automation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CsvModel implements ICsvModel {
    String driverName;
    String url;
    Customer customer;
}
