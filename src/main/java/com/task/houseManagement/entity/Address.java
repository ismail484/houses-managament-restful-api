package com.task.houseManagement.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

    @NotBlank(message = "Street is mandatory")
    private String street;

    @Digits(fraction = 0, integer = 3, message ="add  3 digits numbers only")
    private int houseNo;

    @Digits(fraction = 0, integer = 8, message ="add 8 digits numbers only")
    private int zipCode;

    @NotBlank(message = "City is mandatory")
    private String city;

    public Address() {
    }

    public Address(String street, int houseNo, int zipCode, String city) {
        this.street = street;
        this.houseNo = houseNo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
