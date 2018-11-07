package com.signin.demo.api;

import com.signin.demo.config.Response;
import com.signin.demo.secruity.LoginUser;
import com.signin.demo.service.SignService;
import com.signin.demo.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class signAPI {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public Response userSign (@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst("X-Auth-Token");
        LoginUser loginUser =  TokenHandler.getUserFromToken(token);
        // String number = loginUser.getUsername();
        Response res = signService.userSign(loginUser.getUsername());
        return res;
    }
}
