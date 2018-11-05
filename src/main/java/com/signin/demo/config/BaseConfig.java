package com.signin.demo.config;

public class BaseConfig {


    public static final Long EXPIRE_IN_TWO_WEEKS = 1000l * 60 * 60 * 24 * 100;
    public static final String DEFAULT_ROLE = "user";
    public static final String ERROR_URL_TEMPLATE = "v2/error";
    public static final String ERROR_1001 = "/v2/error?code=1001";
    public static final String ERROR_1002 = "/v2/error?code=1002";
}
