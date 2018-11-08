package com.signin.demo.service;

import com.signin.demo.api.request.UserLogin;
import com.signin.demo.api.request.UserRegister;
import com.signin.demo.config.Response;

public interface UserService {

    public Response userLogin(UserLogin userLogin);

    public Response userRegister(UserRegister userRegister);

    public Response getUserProfile(String number);

    public Response changePass(String number, String pass);

}
