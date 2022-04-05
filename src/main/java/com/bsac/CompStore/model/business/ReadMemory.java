package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ReadMemory {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int volume;

    @Enumerated(EnumType.STRING)
    private ReadMemoryType type;

    @Column
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public ReadMemoryType getType() {
        return type;
    }

    public void setType(ReadMemoryType type) {
        this.type = type;
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
        ReadMemory that = (ReadMemory) o;
        return id == that.id && volume == that.volume && Double.compare(that.price, price) == 0 && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume, type, price);
    }

    @Override
    public String toString() {
        return "ReadMemory{" +
                "id=" + id +
                ", volume=" + volume +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
