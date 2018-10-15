package com.psoik.andrey.getsmartkid.forJSON;

public class Loan {

    // кредит

    private int id;
    private String name;
    private int value;
    private int percent;
    private int totalValue;
    private String expireDate;
    private int timestamp;
    private String status;

    public Loan(int id, String name, int value, int percent, int totalValue, String expireDate, int timestamp, String status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.percent = percent;
        this.totalValue = totalValue;
        this.expireDate = expireDate;
        this.timestamp = timestamp;
        this.status = status;
    }

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
