package com.store.sportswear.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToMany(mappedBy = "order")
    private List<Order_Detail> detailList;

    public List<Order_Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Order_Detail> detailList) {
        this.detailList = detailList;
    }

    private int id;
    private String address;
    private String note;
    private String recipient_name;
    private String phone;
    private String status;
    private long total;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+7")
    private Date order_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+7")
    private Date delivery_at;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+7")
    private Date receive_at;
    
    @ManyToOne
    @JoinColumn(name="recipient_id")
    private UserSystem userSystem;
    
    @ManyToOne
    @JoinColumn(name="shipper_id")
    private UserSystem shipper;
    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Date getOrder_at() {
        return order_at;
    }

    public void setOrder_at(Date order_at) {
        this.order_at = order_at;
    }

    public Date getDelivery_at() {
        return delivery_at;
    }

    public void setDelivery_at(Date delivery_at) {
        this.delivery_at = delivery_at;
    }

    public Date getReceive_at() {
        return receive_at;
    }

    public void setReceive_at(Date receive_at) {
        this.receive_at = receive_at;
    }

    public UserSystem getUser() {
        return userSystem;
    }

    public void setUser(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    public UserSystem getShipper() {
        return shipper;
    }

    public void setShipper(UserSystem shipper) {
        this.shipper = shipper;
    }
}
