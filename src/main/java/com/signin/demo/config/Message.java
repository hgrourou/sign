package com.signin.demo.config;

public class Message {

    /* Message code from 0 to 100 are reserved for Mode applications. */
    public static final Message SUCCESS = new Message(200, "Operation succeeded");


    public static final Message UNAUTHORIZED = new Message(401, "UNAUTHORIZED");
    public static final Message ACCOUNT_NOT_ACTIVATED = new Message(1003, "Your account is not activated");


    private int code;
    private String description;

    Message(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\", \"description\":\"" + description + "\"}";
    }
}
