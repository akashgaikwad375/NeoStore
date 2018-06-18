package com.example.webwerks.neostore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webwerks on 7/11/17.
 */

public class BaseModel {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("user_msg")
    private String user_msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_msg() {
        return user_msg;
    }

    public void setUser_msg(String user_msg) {
        this.user_msg = user_msg;
    }
}
