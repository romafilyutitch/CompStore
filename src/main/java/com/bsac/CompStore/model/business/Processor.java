package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Processor {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String brand;

    @Column
    private String series;

    @Column
    private int coresAmount;

    @Column
    private double frequency;

    @Column
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getCoresAmount() {
        return coresAmount;
    }

    public void setCoresAmount(int coresAmount) {
        this.coresAmount = coresAmount;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
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
        Processor processor = (Processor) o;
        return id == processor.id && coresAmount == processor.coresAmount && Double.compare(processor.frequency, frequency) == 0 && Double.compare(processor.price, price) == 0 && Objects.equals(brand, processor.brand) && Objects.equals(series, processor.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, series, coresAmount, frequency, price);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", coresAmount=" + coresAmount +
                ", frequency=" + frequency +
                ", price=" + price +
                '}';
    }
}
