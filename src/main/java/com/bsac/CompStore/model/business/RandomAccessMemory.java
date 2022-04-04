package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RandomAccessMemory extends Product {

    @Column
    private int volume;

    @Enumerated(EnumType.STRING)
    private RandomAccessMemoryType type;

    @Column
    private int frequency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RandomAccessMemory that = (RandomAccessMemory) o;
        return volume == that.volume && frequency == that.frequency && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, type, frequency);
    }

    @Override
    public String toString() {
        return "RandomAccessMemory{" +
                "volume=" + volume +
                ", type=" + type +
                ", frequency=" + frequency +
                "} " + super.toString();
    }
}
