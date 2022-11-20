package com.store.sportswear.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class UserSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    private String user_email;
    @JsonIgnore
    private String user_password;
    @Transient
    @JsonIgnore
    private String confirmPassword;
    private String user_name;
    private String user_phone;
    private String user_address;
    @ManyToMany
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> role;
    @Transient
    @JsonIgnore
    private List<Order>listOrder;

    public UserSystem() {
    }
    public String getConfirmPassword(){return confirmPassword;}
    public void setConfirmPassword(String confirmPassword){this.confirmPassword=confirmPassword;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    @Override
    public String toString() {
        return "UserSystem [id=" + id + ", user_email=" + user_email + ", user_password=" + user_password + ", confirmPassword="
                + confirmPassword + ", user_name=" + user_name + ", user_phone=" + user_phone + ", user_address=" + user_address;
    }
}
