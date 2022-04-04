package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GraphicsUnit extends Product {

    @Enumerated(EnumType.STRING)
    private GraphicsUnitType type;

    @Column
    private String brand;

    @Column
    private String model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GraphicsUnit that = (GraphicsUnit) o;
        return type == that.type && Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, brand, model);
    }

    @Override
    public String toString() {
        return "GraphicsUnit{" +
                "type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                "} " + super.toString();
    }
}
