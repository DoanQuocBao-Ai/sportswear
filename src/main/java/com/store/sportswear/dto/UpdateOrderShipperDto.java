package com.store.sportswear.dto;

import java.util.List;

public class UpdateOrderShipperDto {
    private int order_id;
    private String noteForShipper;
    private List<UpdateDetailOrder> updateDetailOrders;
    public static class UpdateDetailOrder{
        private int detail_id;
        private int numberOrder;

        public int getDetail_id() {
            return detail_id;
        }

        public void setDetail_id(int detail_id) {
            this.detail_id = detail_id;
        }

        public int getNumberOrder() {
            return numberOrder;
        }

        public void setNumberOrder(int numberOrder) {
            this.numberOrder = numberOrder;
        }
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getNoteForShipper() {
        return noteForShipper;
    }

    public void setNoteForShipper(String noteForShipper) {
        this.noteForShipper = noteForShipper;
    }

    public List<UpdateDetailOrder> getUpdateDetailOrders() {
        return updateDetailOrders;
    }

    public void setUpdateDetailOrders(List<UpdateDetailOrder> updateDetailOrders) {
        this.updateDetailOrders = updateDetailOrders;
    }
}
