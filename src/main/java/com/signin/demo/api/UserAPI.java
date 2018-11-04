package com.signin.demo.api;

import com.signin.demo.api.request.UserLogin;
import com.signin.demo.api.request.UserRegister;
import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response userLogin (@RequestBody UserLogin userLogin) {
        Response res = userService.userLogin(userLogin);
        return res;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response userRegister(@RequestBody UserRegister userRegister) {
        Response res = userService.userRegister(userRegister);
        return res;
    }
}
