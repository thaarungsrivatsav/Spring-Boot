package com.SpringBootProject.IMS.valueobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    String stockName;
    int stockQuantity;
    double pricePerUnit;
}
