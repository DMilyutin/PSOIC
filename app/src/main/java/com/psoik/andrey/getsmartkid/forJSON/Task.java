package com.psoik.andrey.getsmartkid.forJSON;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

public class Task implements Parcelable {

    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    private int id;
    private int childId;
    private String name;
    private String status;

    private String expireDate;
    private int price;
    private String description;

    public Task(int id, int childId, String name, String status, String expireDate, int price, String description) {
        this.id = id;
        this.childId = childId;
        this.name = name;
        this.status = status;
        //this.expireDate = dateFormat.format(new Date());
        this.expireDate = expireDate;
        this.price = price;
        this.description = description;
    }

    protected Task(Parcel in) {
        id = in.readInt();
        childId = in.readInt();
        name = in.readString();
        status = in.readString();
        expireDate = in.readString();
        price = in.readInt();
        description = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status; }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(childId);
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeString(expireDate);
        parcel.writeInt(price);
        parcel.writeString(description);
    }
}
