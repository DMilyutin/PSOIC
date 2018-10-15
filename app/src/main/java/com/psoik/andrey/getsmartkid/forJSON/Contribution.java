package com.psoik.andrey.getsmartkid.forJSON;

public class Contribution {
    // вклады
    private int id;
    private String name;
    private int value;
    private double percent;
    private int totalValue;
    private String expireDate;
    private int timestamp;
    private String status;


    public Contribution(int id, String name, int value, double percent, int totalValue, String expireDate, int timestamp, String status) {

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
