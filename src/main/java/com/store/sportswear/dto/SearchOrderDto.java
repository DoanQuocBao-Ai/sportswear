package com.store.sportswear.dto;

public class SearchOrderDto {
    private String status;
    private String fromDate;
    private String toDate;
    public SearchOrderDto()
    {
        status ="";
        fromDate ="";
        toDate ="";
    }
    @Override
    public String toString()
    {
        return "SearchOrderDto [status ="+status+", fromDate ="+fromDate+", toDate = "+toDate+"]";
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
