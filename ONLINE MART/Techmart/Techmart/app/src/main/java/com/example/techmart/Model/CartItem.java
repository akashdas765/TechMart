package com.example.techmart.Model;

public class CartItem {
    public String proname,price,quantity,imagelink,prodid,date,ordertotal,itemsbought,time,name;

    public CartItem(String proname,String price,String quantity,String imagelink,String prodid,String date,String ordertotal,String itemsbought,String time,String name) {
        this.proname = proname;
        this.price= price;
        this.quantity=quantity;
        this.imagelink=imagelink;
        this.prodid=prodid;
        this.date=date;
        this.ordertotal=ordertotal;
        this.itemsbought=itemsbought;
        this.time=time;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartItem() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getPrice() {
        return price;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
