package com.bsac.CompStore.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@Entity
public class Computer {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Computer purpose is mandatory")
    private ComputerPurpose purpose;

    @Column
    @NotBlank(message = "Computer brand is mandatory")
    private String brand;

    @Column
    @NotBlank(message = "Computer name is mandatory")
    private String name;

    @Column
    @Positive(message = "Computer year must be positive")
    private int year;

    @Column
    @NotBlank(message = "Computer operation system is mandatory")
    private String operationSystem;

    @Column
    @Positive(message = "Computer price must be positive")
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "graphics_unit_id", referencedColumnName = "id")
    private GraphicsUnit graphicsUnit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "processor_id", referencedColumnName = "id")
    private Processor processor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "random_access_memory_id", referencedColumnName = "id")
    private RandomAccessMemory randomAccessMemory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "read_memory_id", referencedColumnName = "id")
    private ReadMemory readMemory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_id")
    private List<Review> reviews;

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return id == computer.id && year == computer.year && Double.compare(computer.price, price) == 0 && purpose == computer.purpose && Objects.equals(brand, computer.brand) && Objects.equals(name, computer.name) && Objects.equals(operationSystem, computer.operationSystem) && Objects.equals(graphicsUnit, computer.graphicsUnit) && Objects.equals(processor, computer.processor) && Objects.equals(randomAccessMemory, computer.randomAccessMemory) && Objects.equals(readMemory, computer.readMemory) && Objects.equals(reviews, computer.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purpose, brand, name, year, operationSystem, price, graphicsUnit, processor, randomAccessMemory, readMemory, reviews);
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
                ", reviews=" + reviews +
                '}';
    }
}
