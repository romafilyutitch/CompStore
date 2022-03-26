package com.bsac.CompStore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Computer {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int year;

    @Column
    private String processor;

    @Column
    private String color;

    @Column
    private String ram;

    @Column
    private String hdd;

    @Column
    private String graphics;

    @Column
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
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
        Computer computer = (Computer) o;
        return id == computer.id && year == computer.year && Double.compare(computer.price, price) == 0 && Objects.equals(name, computer.name) && Objects.equals(processor, computer.processor) && Objects.equals(color, computer.color) && Objects.equals(ram, computer.ram) && Objects.equals(hdd, computer.hdd) && Objects.equals(graphics, computer.graphics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, processor, color, ram, hdd, graphics, price);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", processor='" + processor + '\'' +
                ", color='" + color + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                ", graphics='" + graphics + '\'' +
                ", price=" + price +
                '}';
    }
}
