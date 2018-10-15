package com.psoik.andrey.getsmartkid.forJSON;

public class Balances{
    private String asset;
    private double value;

    public Balances(String asset, double value) {
        this.asset = asset;
        this.value = value;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
