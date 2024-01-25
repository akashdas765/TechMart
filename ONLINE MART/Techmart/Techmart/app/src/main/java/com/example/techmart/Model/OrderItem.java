package com.example.techmart.Model;

public class OrderItem {
    public String date,ordertotal,itemsbought;

    public OrderItem() {
    }

    public OrderItem(String date, String ordertotal, String itemsbought) {
        this.date = date;
        this.ordertotal = ordertotal;
        this.itemsbought = itemsbought;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(String ordertotal) {
        this.ordertotal = ordertotal;
    }

    public String getItemsbought() {
        return itemsbought;
    }

    public void setItemsbought(String itemsbought) {
        this.itemsbought = itemsbought;
    }
}
