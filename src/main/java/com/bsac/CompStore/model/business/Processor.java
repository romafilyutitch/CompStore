package com.bsac.CompStore.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Processor extends Product {
    @Column
    private String brand;

    @Column
    private String series;

    @Column
    private int coresAmount;

    @Column
    private double frequency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processor processor = (Processor) o;
        return coresAmount == processor.coresAmount && Double.compare(processor.frequency, frequency) == 0 && Objects.equals(brand, processor.brand) && Objects.equals(series, processor.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, series, coresAmount, frequency);
    }

    @Override
    public String toString() {
        return "Processor{" +
                "brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", coresAmount=" + coresAmount +
                ", frequency=" + frequency +
                "} " + super.toString();
    }
}
