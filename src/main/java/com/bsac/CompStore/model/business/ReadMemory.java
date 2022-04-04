package com.bsac.CompStore.model.business;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ReadMemory extends Product {

    @Column
    private int volume;

    @Enumerated(EnumType.STRING)
    private ReadMemoryType type;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public ReadMemoryType getType() {
        return type;
    }

    public void setType(ReadMemoryType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReadMemory that = (ReadMemory) o;
        return volume == that.volume && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, type);
    }

    @Override
    public String toString() {
        return "ReadMemory{" +
                "volume=" + volume +
                ", type=" + type +
                "} " + super.toString();
    }
}
