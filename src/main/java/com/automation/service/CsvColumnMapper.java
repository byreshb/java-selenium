package com.automation.service;

import com.automation.model.CsvModel;
import com.automation.model.Customer;
import com.automation.shared.CsvColumn;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class CsvColumnMapper {

    private final static String ESCAPE_DOUBLE_QUOTE_CHAR = "\"";
    private final static String EMPTY_STRING = "";

    public static CsvModel map(final List<List<String>> csvData) {

        final CsvModel csvModel = CsvModel.builder().build();
        final Customer customer = Customer.builder().build();
        csvModel.setCustomer(customer);

        final Map<String, String> csvKeyValues = new HashMap<>();
        for (int i = 0; i < csvData.get(0).size(); i++) {
            csvKeyValues.put(
                    csvData.get(0).get(i).replace(ESCAPE_DOUBLE_QUOTE_CHAR, EMPTY_STRING).trim(),
                    csvData.get(1).get(i).replace(ESCAPE_DOUBLE_QUOTE_CHAR, EMPTY_STRING).trim()
            );
        }

        for (CsvColumn csvColumn : CsvColumn.values()) {
            switch (csvColumn) {
                case DRIVER:
                    csvModel.setDriverName(csvKeyValues.get(csvColumn.getLabel()));
                    break;
                case URL:
                    csvModel.setUrl(csvKeyValues.get(csvColumn.getLabel()));
                    break;
                case CUSTOMER_FIRST_NAME:
                    customer.setCustomerFirstName(csvKeyValues.get(csvColumn.getLabel()));
                    break;
                case CUSTOMER_LAST_NAME:
                    customer.setCustomerLastName(csvKeyValues.get(csvColumn.getLabel()));
                    break;
                case CUSTOMER_POSTAL_CODE:
                    customer.setCustomerPostalCode(csvKeyValues.get(csvColumn.getLabel()));
                    break;
                default:
                    throw new RuntimeException("Invalid CSV data. CSv should have driver and url columns");
            }
        }

        return csvModel;
    }
}
