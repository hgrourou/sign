package com.signin.demo.api;

import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response userLogin () {
        Response res = userService.userLogin();
        res.setMessage(Message.SUCCESS);
        return res;
    }
}
