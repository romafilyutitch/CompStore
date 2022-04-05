package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GraphicsUnit {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private GraphicsUnitType type;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GraphicsUnitType getType() {
        return type;
    }

    public void setType(GraphicsUnitType type) {
        this.type = type;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicsUnit that = (GraphicsUnit) o;
        return id == that.id && Double.compare(that.price, price) == 0 && type == that.type && Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, brand, model, price);
    }

    @Override
    public String toString() {
        return "GraphicsUnit{" +
                "id=" + id +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
