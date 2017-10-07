package com.etuloser.padma.rohit.midterm_rpadma;

import java.io.Serializable;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomObject  implements Serializable{

    private long _id;
 String name,price,imageurl,discount,mspprice,UID;

    public CustomObject(long _id, String name, String price, String imageurl, String discount, String mspprice, String UID) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
        this.discount = discount;
        this.mspprice = mspprice;
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMspprice() {
        return mspprice;
    }

    public void setMspprice(String mspprice) {
        this.mspprice = mspprice;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", discount='" + discount + '\'' +
                ", mspprice='" + mspprice + '\'' +
                '}';
    }

    public CustomObject() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
