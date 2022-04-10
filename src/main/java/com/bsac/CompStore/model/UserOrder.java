package com.bsac.CompStore.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserOrder {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "computer_id", referencedColumnName = "id")
    private Computer computer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id && status == userOrder.status && Objects.equals(computer, userOrder.computer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, computer);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", status=" + status +
                ", computer=" + computer +
                '}';
    }
}
