package com.golem.app.collection.ticket;

import com.golem.app.collection.ConsoleRead;

public class Address implements Comparable <Address>, ConsoleRead {
    private String street; //Поле может быть null

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public Address () {

    }
    public Address (boolean init) {
        if (init) this.testLoad();
    }

    public void testLoad() {
       street = "unknown street";
    }

    @Override
    public int compareTo(Address o) {
        return getStreet().compareTo(o.getStreet());
    }

    @Override
    public String toReadString() {
        return "Address >>\n" +
                "   street: " + street;
    }
}