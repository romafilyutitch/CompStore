package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
