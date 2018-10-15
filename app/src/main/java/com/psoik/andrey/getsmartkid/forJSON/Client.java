package com.psoik.andrey.getsmartkid.forJSON;

import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("mail")
    private String mail;

    @SerializedName("password")
    private String password;


    public Client(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
