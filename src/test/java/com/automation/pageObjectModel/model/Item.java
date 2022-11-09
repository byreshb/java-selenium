package com.automation.pageObjectModel.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Item {
    String itemId;
    String itemName;
    double itemPrice;
}
