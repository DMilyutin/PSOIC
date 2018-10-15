package com.psoik.andrey.getsmartkid.forJSON;

public class Children {

    private int id;
    private String name;
    private int parentId;
    private Balances[] balances;


    public Children(int id, String name, int parentId, Balances[] balances) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.balances = balances;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Balances[] getBalances() {
        return balances;
    }

    public void setBalances(Balances[] balances) {
        this.balances = balances;
    }
}
