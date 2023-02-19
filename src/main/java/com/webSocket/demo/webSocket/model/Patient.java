package com.webSocket.demo.webSocket.model;

public class Patient {


    private String name;

    private String mobile;

    public Patient() {
    }

    public Patient(String state, String price) {
        this.name = state;
        this.mobile = price;
    }

    public String getState() {
        return name;
    }

    public void setState(String state) {
        this.name = state;
    }

    public String getPrice() {
        return mobile;
    }

    public void setPrice(String price) {
        this.mobile = price;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
