package com.store.sportswear.Entity;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String address;
    private String note;
    private String recipient_name;
    private String phone;
    private String status;
    private long total;
    @ManyToOne
    @JoinColumn(name="recipient_id")
    private User user;

}
