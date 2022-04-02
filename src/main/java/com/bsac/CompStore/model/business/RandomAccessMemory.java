package com.bsac.CompStore.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class RandomAccessMemory extends Product {
    @Column
    private int volume;

    @Enumerated
    private Type type;

    @Column
    private int frequency;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int value) {
        this.volume = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomAccessMemory that = (RandomAccessMemory) o;
        return volume == that.volume && frequency == that.frequency && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, type, frequency);
    }

    @Override
    public String toString() {
        return "RandomAccessMemory{" +
                "volume=" + volume +
                ", type=" + type +
                ", frequency=" + frequency +
                "} " + super.toString();
    }

    enum Type{
        DDR1, DDR2, DDR3, DDR4, DDR5
    }
}
