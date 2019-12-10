package com.task.houseManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.Comparator;
import java.util.Date;

@Entity
public class House implements Comparable<House> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Valid
    @Embedded
    private Address address = new Address();

    @Enumerated(EnumType.STRING)
    private Type type;

    @NotBlank(message = "Price is mandatory")
    private String price;

    @Column(nullable = false)
    private boolean newHouse;

    @Digits(fraction = 4, integer = 10, message ="add the area with 1ß0 digits and4 fractions only")
    private int area;

    @Column(nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date constructionDate;

    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isNewHouse() {
        return newHouse;
    }

    public void setNewHouse(boolean newHouse) {
        this.newHouse = newHouse;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Date getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address=" + address +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", newHouse=" + newHouse +
                ", area=" + area +
                ", constructionDate=" + constructionDate +
                '}';
    }

    @Override
    public int compareTo(House o) {
        return this.id.compareTo(o.id);
    }
}
