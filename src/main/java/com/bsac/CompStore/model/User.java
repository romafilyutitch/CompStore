package com.bsac.CompStore.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotBlank(message = "User name is mandatory")
    private String username;

    @Column
    @NotBlank(message = "User email is mandatory")
    @Email(message = "User email must be valid")
    private String email;

    @Column
    @NotBlank(message = "User password is mandatory")
    @Min(value = 5, message = "User password must be bigger than 5 characters length")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<UserOrder> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && userRole == user.userRole && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, userRole, orders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", orders=" + orders +
                '}';
    }
}
