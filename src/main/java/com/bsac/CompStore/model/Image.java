package com.bsac.CompStore.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @Column(length = 1000000000)
    private byte[] picByte;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id && Objects.equals(name, image.name) && Objects.equals(type, image.type) && Arrays.equals(picByte, image.picByte) && Objects.equals(computer, image.computer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, type, computer);
        result = 31 * result + Arrays.hashCode(picByte);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", picByte=" + Arrays.toString(picByte) +
                ", computer=" + computer +
                '}';
    }
}
