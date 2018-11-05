package com.signin.demo.service.imp.user;

import com.signin.demo.api.request.UserLogin;
import com.signin.demo.api.request.UserRegister;
import com.signin.demo.config.BaseConfig;
import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.dao.UserDAO;
import com.signin.demo.entity.User;
import com.signin.demo.service.user.UserService;
import com.signin.demo.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDAO userDAO;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;


    private String updateAccessToken(Long number, String role) {
        long expires = System.currentTimeMillis() + BaseConfig.EXPIRE_IN_TWO_WEEKS;
        String token = TokenHandler.createToken(number.toString(), role, expires);
        final Long now = System.currentTimeMillis();
        userDAO.updateAccessToken(token, number, expires, now);
        return token;
    }

    @Override
    public Response userLogin(UserLogin userLogin) {
        Response res = new Response();
        Long number = userLogin.getNumber();
        String password = userLogin.getPassword();
        if (number == null || password == null) {
            res.setMessage(Message.LOGIN_ERROR);
            return res;
        }
        User user = new User();
        user = userDAO.getUserByNumber(number);
        if (user == null) {
            res.setMessage(Message.USER_NOT_EXIST);
            return res;
        }
        try {
            // Authenticate the user by its username and password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getNumber().toString(), password);
            Authentication authentication = authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            res.setMessage(Message.UNAUTHORIZED);
            return res;
        }

        String userToken = updateAccessToken(user.getNumber(), user.getRole());
        user.setAccessToken(userToken);

        res.setMessage(Message.SUCCESS);
        Map<String, Object> payload = new HashMap<>();
        payload.put("user", user);
        res.setPayload(payload);
        return res;
    }

    @Override
    public Response userRegister(UserRegister userRegister) {
        Response res = new Response();

        User user1 = userDAO.getUserByNumber(userRegister.getNumber());
        if (user1 != null) {
            res.setMessage(Message.USER_EXIST);
            return res;
        }
        User user = new User();
        user.setName(userRegister.getName());
        user.setNumber(userRegister.getNumber());
        user.setPhone(userRegister.getPhone());
        user.setPassword(userRegister.getPassword());
        user.setRole(BaseConfig.DEFAULT_ROLE);
        Long time = new Date().getTime();
        user.setCreateTime(time);
        user.setUpdateTime(time);

        try {
            userDAO.userRegister(user);
        } catch (Exception e) {
            res.setMessage(Message.ERROR);
        }
        res.setMessage(Message.SUCCESS);
        return res;
    }
}
