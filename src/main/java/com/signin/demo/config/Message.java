package com.signin.demo.config;

public class Message {

    /* Message code from 0 to 100 are reserved for Mode applications. */
    public static final Message SUCCESS = new Message(200, "Operation succeeded");


    public static final Message UNAUTHORIZED = new Message(401, "UNAUTHORIZED");


    public static final Message LOGIN_ERROR = new Message(400, "LOGINERROR");

    public static final Message USER_EXIST = new Message(400, "User exist");

    public static final Message ERROR = new Message(400, "system error");
    public static final Message HAS_SIGN = new Message(5, "user is sign");


    public static final Message USER_NOT_EXIST = new Message(4, "This user does not exist");



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
