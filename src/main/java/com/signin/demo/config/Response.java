package com.signin.demo.config;

import java.util.Map;

public class Response {
    private Message message;
    private Map<String, ?> payload;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Map<String, ?> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, ?> payload) {
        this.payload = payload;
    }
}
