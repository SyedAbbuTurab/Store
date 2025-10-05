package com.codewithturab.order.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("orders")
public class Order {

    @Id
    private String id;
    private String productName;
    private int quantity;

}
