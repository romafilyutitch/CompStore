package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GraphicsUnit extends Product {

    @Enumerated(EnumType.STRING)
    private Type graphicsType;

    @Column
    private String brand;

    @Column
    private String model;

    public Type getGraphicsType() {
        return graphicsType;
    }

    public void setGraphicsType(Type graphicsType) {
        this.graphicsType = graphicsType;
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
        return graphicsType == that.graphicsType && Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphicsType, brand, model);
    }

    @Override
    public String toString() {
        return "GraphicsUnit{" +
                "graphicsType=" + graphicsType +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                "} " + super.toString();
    }

    enum Type{
        EMBEDDED, DISCRETE
    }
}
