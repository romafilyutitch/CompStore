package com.bsac.CompStore.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
public class ReadMemory {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @Positive(message = "Read Memory volume must be positive")
    private int volume;

    @Enumerated(EnumType.STRING)
    private ReadMemoryType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        ReadMemory that = (ReadMemory) o;
        return id == that.id && volume == that.volume && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume, type);
    }

    @Override
    public String toString() {
        return "ReadMemory{" +
                "id=" + id +
                ", volume=" + volume +
                ", type=" + type +
                '}';
    }
}
