package com.store.sportswear.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "role")
    private Set<UserSystem> listUserSystem;
    public Role(String role){
        this.name=role;
    }
    public Role(){}
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

    public Set<UserSystem> getListUser() {
        return listUserSystem;
    }

    public void setListUser(Set<UserSystem> listUserSystem) {
        this.listUserSystem = listUserSystem;
    }
}
