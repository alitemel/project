package com.ath2o.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "class_type")
    private String classType;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, String classType) {
        this.brand = brand;
        this.model = model;
        this.classType = classType;
    }

    public Vehicle(String line)
    {
        String[] specs = line.split(";");
        this.brand = specs[0];
        this.model = specs[1];
        this.classType = specs[2];
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public String toString()
    {
        return this.brand + "; " + this.model + "; " + this.classType + System.lineSeparator();
    }
}
