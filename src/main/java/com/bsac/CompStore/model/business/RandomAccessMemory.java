package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RandomAccessMemory {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int volume;

    @Enumerated(EnumType.STRING)
    private RandomAccessMemoryType type;

    @Column
    private int frequency;

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

    public RandomAccessMemoryType getType() {
        return type;
    }

    public void setType(RandomAccessMemoryType type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
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
        RandomAccessMemory that = (RandomAccessMemory) o;
        return id == that.id && volume == that.volume && frequency == that.frequency && Double.compare(that.price, price) == 0 && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume, type, frequency, price);
    }

    @Override
    public String toString() {
        return "RandomAccessMemory{" +
                "id=" + id +
                ", volume=" + volume +
                ", type=" + type +
                ", frequency=" + frequency +
                ", price=" + price +
                '}';
    }
}
