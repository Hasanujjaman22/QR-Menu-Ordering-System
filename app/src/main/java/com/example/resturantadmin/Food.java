package com.example.resturantadmin;

import java.io.Serializable;

public class Food implements Serializable {

    private String name;
    private int price;
    private byte[] image;

    public Food(String name, int price, byte[] image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }
}
