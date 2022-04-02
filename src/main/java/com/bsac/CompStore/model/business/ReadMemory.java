package com.bsac.CompStore.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class ReadMemory extends Product {

    @Column
    private int volume;

    @Enumerated(EnumType.STRING)
    private Type type;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadMemory that = (ReadMemory) o;
        return volume == that.volume && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, type);
    }

    @Override
    public String toString() {
        return "ReadMemory{" +
                "volume=" + volume +
                ", type=" + type +
                "} " + super.toString();
    }

    enum Type {
        HDD, SSD
    }
}
