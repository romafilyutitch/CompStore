package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GraphicsUnit extends Product {

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String brand;

    @Column
    private String model;

    public Type getType() {
        return type;
    }

    public void setType(Type graphicsType) {
        this.type = graphicsType;
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
        GraphicsUnit that = (GraphicsUnit) o;
        return type == that.type && Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, brand, model);
    }

    @Override
    public String toString() {
        return "GraphicsUnit{" +
                "graphicsType=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                "} " + super.toString();
    }

    enum Type{
        EMBEDDED, DISCRETE
    }
}
