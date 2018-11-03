package com.signin.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class BaseAPI {

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public Long getTime() {
        Long date = new Date().getTime();
        return date;
    }

}
