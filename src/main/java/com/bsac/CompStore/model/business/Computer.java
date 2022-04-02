package com.bsac.CompStore.model.business;

import com.bsac.CompStore.repository.RandomAccessMemoryRepository;
import com.bsac.CompStore.repository.ReadMemoryRepository;

import javax.persistence.*;
import java.util.Objects;
import java.util.Random;

@Entity
public class Computer extends Product {

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column
    private String brand;

    @Column
    private String system;

    @OneToOne
    @JoinColumn(name = "id")
    private GraphicsUnit graphicsUnit;

    @OneToOne
    @JoinColumn(name = "id")
    private Processor processor;

    @OneToOne
    @JoinColumn(name = "id")
    private RandomAccessMemory randomAccessMemory;

    @OneToOne
    @JoinColumn(name = "id")
    private ReadMemory readMemory;

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
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
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return purpose == computer.purpose && Objects.equals(brand, computer.brand) && Objects.equals(system, computer.system) && Objects.equals(graphicsUnit, computer.graphicsUnit) && Objects.equals(processor, computer.processor) && Objects.equals(randomAccessMemory, computer.randomAccessMemory) && Objects.equals(readMemory, computer.readMemory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purpose, brand, system, graphicsUnit, processor, randomAccessMemory, readMemory);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "purpose=" + purpose +
                ", brand='" + brand + '\'' +
                ", system='" + system + '\'' +
                ", graphicsUnit=" + graphicsUnit +
                ", processor=" + processor +
                ", randomAccessMemory=" + randomAccessMemory +
                ", readMemory=" + readMemory +
                "} " + super.toString();
    }

    enum Purpose {
        WORK, MULTIMEDIA, CODING, GAMING
    }
}
