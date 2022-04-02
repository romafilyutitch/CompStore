package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Computer extends Product {

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column
    private String brand;

    @Column
    private String system;

    @OneToOne
    private Processor processor;

    @OneToOne
    private GraphicsUnit graphics;

    @OneToOne
    private RandomAccessMemory randomAccessMemory;

    @OneToOne
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

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public GraphicsUnit getGraphics() {
        return graphics;
    }

    public void setGraphics(GraphicsUnit graphics) {
        this.graphics = graphics;
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
        return Objects.equals(purpose, computer.purpose) && Objects.equals(brand, computer.brand) && Objects.equals(system, computer.system) && Objects.equals(processor, computer.processor) && Objects.equals(graphics, computer.graphics) && Objects.equals(randomAccessMemory, computer.randomAccessMemory) && Objects.equals(readMemory, computer.readMemory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purpose, brand, system, processor, graphics, randomAccessMemory, readMemory);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "purpose=" + purpose +
                ", brand='" + brand + '\'' +
                ", system='" + system + '\'' +
                ", processor=" + processor +
                ", graphics=" + graphics +
                ", randomAccessMemory=" + randomAccessMemory +
                ", readMemory=" + readMemory +
                "} " + super.toString();
    }

    enum Purpose {
        WORK, MULTIMEDIA, CODING, GAMING
    }
}
