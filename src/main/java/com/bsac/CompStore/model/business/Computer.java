package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Computer {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private ComputerPurpose purpose;

    @Column
    private String brand;

    @Column
    private String name;

    @Column
    private int year;

    @Column
    private String operationSystem;

    @Column
    private double price;

    @OneToOne
    @JoinColumn(name = "graphics_unit_id", referencedColumnName = "id")
    private GraphicsUnit graphicsUnit;

    @OneToOne
    @JoinColumn(name = "processor_id", referencedColumnName = "id")
    private Processor processor;

    @OneToOne
    @JoinColumn(name = "random_access_memory_id", referencedColumnName = "id")
    private RandomAccessMemory randomAccessMemory;

    @OneToOne
    @JoinColumn(name = "read_memory_id", referencedColumnName = "id")
    private ReadMemory readMemory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ComputerPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(ComputerPurpose purpose) {
        this.purpose = purpose;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public GraphicsUnit getGraphicsUnit() {
        return graphicsUnit;
    }

    public void setGraphicsUnit(GraphicsUnit graphicsUnit) {
        this.graphicsUnit = graphicsUnit;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public RandomAccessMemory getRandomAccessMemory() {
        return randomAccessMemory;
    }

    public void setRandomAccessMemory(RandomAccessMemory randomAccessMemory) {
        this.randomAccessMemory = randomAccessMemory;
    }

    public ReadMemory getReadMemory() {
        return readMemory;
    }

    public void setReadMemory(ReadMemory readMemory) {
        this.readMemory = readMemory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return id == computer.id && year == computer.year && Double.compare(computer.price, price) == 0 && purpose == computer.purpose && Objects.equals(brand, computer.brand) && Objects.equals(name, computer.name) && Objects.equals(operationSystem, computer.operationSystem) && Objects.equals(graphicsUnit, computer.graphicsUnit) && Objects.equals(processor, computer.processor) && Objects.equals(randomAccessMemory, computer.randomAccessMemory) && Objects.equals(readMemory, computer.readMemory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purpose, brand, name, year, operationSystem, price, graphicsUnit, processor, randomAccessMemory, readMemory);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", purpose=" + purpose +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", operationSystem='" + operationSystem + '\'' +
                ", price=" + price +
                ", graphicsUnit=" + graphicsUnit +
                ", processor=" + processor +
                ", randomAccessMemory=" + randomAccessMemory +
                ", readMemory=" + readMemory +
                '}';
    }
}
