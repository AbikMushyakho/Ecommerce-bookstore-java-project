package com.ismt.model;

public class Order {
    int id;
    String order_number;
    String total;
int customer_id;

    public Order(int id, String order_number, String total) {
        this.id = id;
        this.order_number = order_number;
        this.total = total;
    }

    public Order() {
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Order(int id, String order_number, String total, int customer_id) {
        this.id = id;
        this.order_number = order_number;
        this.total = total;
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
