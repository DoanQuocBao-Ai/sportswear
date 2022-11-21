package com.store.sportswear.entity;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long total;
    @OneToOne
    @JoinColumn(name="user_id")
    private UserSystem userSystem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public UserSystem getUser() {
        return userSystem;
    }

    public void setUser(UserSystem userSystem) {
        this.userSystem = userSystem;
    }
}
