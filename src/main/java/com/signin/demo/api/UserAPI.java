package com.signin.demo.api;

import com.signin.demo.api.request.UserLogin;
import com.signin.demo.api.request.UserRegister;
import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.secruity.LoginUser;
import com.signin.demo.service.UserService;
import com.signin.demo.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Response getUserProfile(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst("X-Auth-Token");
        LoginUser loginUser =  TokenHandler.getUserFromToken(token);
        return userService.getUserProfile(loginUser.getUsername());
    }

    @RequestMapping(value = "pass", method = RequestMethod.POST)
    public Response changePass(@RequestHeader HttpHeaders headers,
                               @RequestBody UserLogin userLogin) {
        String token = headers.getFirst("X-Auth-Token");
        LoginUser loginUser =  TokenHandler.getUserFromToken(token);
        if (loginUser == null) {
            Response res = new Response();
            res.setMessage(Message.UNAUTHORIZED);
            return res;
        }
        String pass = userLogin.getPassword();
        return userService.changePass(loginUser.getUsername(), pass);
    }
}
