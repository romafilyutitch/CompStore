package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Computer extends Product {

    @Enumerated(EnumType.STRING)
    private ComputerPurpose purpose;

    @Column
    private String brand;

    @Column
    private String operationSystem;

    @Enumerated(EnumType.STRING)
    private GraphicsUnitType graphicsUnitType;

    @Column
    private String graphicsUnitBrand;

    @Column
    private String graphicsUnitModel;

    @Column
    private String processorBrand;

    @Column
    private String processorSeries;

    @Column
    private int processorCoresAmount;

    @Column
    private double processorFrequency;

    @Column
    private int randomAccessMemoryVolume;

    @Enumerated(EnumType.STRING)
    private RandomAccessMemoryType randomAccessMemoryType;

    @Column
    private int randomAccessMemoryFrequency;

    @Column
    private int readMemoryVolume;

    @Enumerated(EnumType.STRING)
    private ReadMemoryType readMemoryType;

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

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public GraphicsUnitType getGraphicsUnitType() {
        return graphicsUnitType;
    }

    public void setGraphicsUnitType(GraphicsUnitType graphicsUnitType) {
        this.graphicsUnitType = graphicsUnitType;
    }

    public String getGraphicsUnitBrand() {
        return graphicsUnitBrand;
    }

    public void setGraphicsUnitBrand(String graphicsUnitBrand) {
        this.graphicsUnitBrand = graphicsUnitBrand;
    }

    public String getGraphicsUnitModel() {
        return graphicsUnitModel;
    }

    public void setGraphicsUnitModel(String graphicsUnitModel) {
        this.graphicsUnitModel = graphicsUnitModel;
    }

    public String getProcessorBrand() {
        return processorBrand;
    }

    public void setProcessorBrand(String processorBrand) {
        this.processorBrand = processorBrand;
    }

    public String getProcessorSeries() {
        return processorSeries;
    }

    public void setProcessorSeries(String processorSeries) {
        this.processorSeries = processorSeries;
    }

    public int getProcessorCoresAmount() {
        return processorCoresAmount;
    }

    public void setProcessorCoresAmount(int processorCoresAmount) {
        this.processorCoresAmount = processorCoresAmount;
    }

    public double getProcessorFrequency() {
        return processorFrequency;
    }

    public void setProcessorFrequency(double processorFrequency) {
        this.processorFrequency = processorFrequency;
    }

    public int getRandomAccessMemoryVolume() {
        return randomAccessMemoryVolume;
    }

    public void setRandomAccessMemoryVolume(int randomAccessMemoryVolume) {
        this.randomAccessMemoryVolume = randomAccessMemoryVolume;
    }

    public RandomAccessMemoryType getRandomAccessMemoryType() {
        return randomAccessMemoryType;
    }

    public void setRandomAccessMemoryType(RandomAccessMemoryType randomAccessMemoryType) {
        this.randomAccessMemoryType = randomAccessMemoryType;
    }

    public int getRandomAccessMemoryFrequency() {
        return randomAccessMemoryFrequency;
    }

    public void setRandomAccessMemoryFrequency(int randomAccessMemoryFrequency) {
        this.randomAccessMemoryFrequency = randomAccessMemoryFrequency;
    }

    public int getReadMemoryVolume() {
        return readMemoryVolume;
    }

    public void setReadMemoryVolume(int readMemoryVolume) {
        this.readMemoryVolume = readMemoryVolume;
    }

    public ReadMemoryType getReadMemoryType() {
        return readMemoryType;
    }

    public void setReadMemoryType(ReadMemoryType readMemoryType) {
        this.readMemoryType = readMemoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return processorCoresAmount == computer.processorCoresAmount && Double.compare(computer.processorFrequency, processorFrequency) == 0 && randomAccessMemoryVolume == computer.randomAccessMemoryVolume && randomAccessMemoryFrequency == computer.randomAccessMemoryFrequency && readMemoryVolume == computer.readMemoryVolume && purpose == computer.purpose && Objects.equals(brand, computer.brand) && Objects.equals(operationSystem, computer.operationSystem) && graphicsUnitType == computer.graphicsUnitType && Objects.equals(graphicsUnitBrand, computer.graphicsUnitBrand) && Objects.equals(graphicsUnitModel, computer.graphicsUnitModel) && Objects.equals(processorBrand, computer.processorBrand) && Objects.equals(processorSeries, computer.processorSeries) && randomAccessMemoryType == computer.randomAccessMemoryType && readMemoryType == computer.readMemoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), purpose, brand, operationSystem, graphicsUnitType, graphicsUnitBrand, graphicsUnitModel, processorBrand, processorSeries, processorCoresAmount, processorFrequency, randomAccessMemoryVolume, randomAccessMemoryType, randomAccessMemoryFrequency, readMemoryVolume, readMemoryType);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "purpose=" + purpose +
                ", brand='" + brand + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", graphicsUnitType=" + graphicsUnitType +
                ", graphicsUnitBrand='" + graphicsUnitBrand + '\'' +
                ", graphicsUnitModel='" + graphicsUnitModel + '\'' +
                ", processorBrand='" + processorBrand + '\'' +
                ", processorSeries='" + processorSeries + '\'' +
                ", processorCoresAmount=" + processorCoresAmount +
                ", processorFrequency=" + processorFrequency +
                ", randomAccessMemoryVolume=" + randomAccessMemoryVolume +
                ", randomAccessMemoryType=" + randomAccessMemoryType +
                ", randomAccessMemoryFrequency=" + randomAccessMemoryFrequency +
                ", readMemoryVolume=" + readMemoryVolume +
                ", readMemoryType=" + readMemoryType +
                "} " + super.toString();
    }
}
