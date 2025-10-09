package com.codewithturab.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
public class inventory {

    @Id
    private String id;
    private String productName;
    private int quantity;
    private double price;

    public inventory() {

    }

    public inventory(String productName, int quantity, double price ) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
