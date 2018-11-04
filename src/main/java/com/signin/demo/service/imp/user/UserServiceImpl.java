package com.signin.demo.service.imp.user;

import com.signin.demo.api.request.UserLogin;
import com.signin.demo.api.request.UserRegister;
import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.dao.UserDAO;
import com.signin.demo.entity.User;
import com.signin.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDAO userDAO;

    @Override
    public Response userLogin(UserLogin userLogin) {
        Response res = new Response();
        String userName = userLogin.getUsername();
        String password = userLogin.getPassword();
        if (userName == null || password == null) {
            res.setMessage(Message.LOGINERROR);
            return res;
        }
        res.setMessage(Message.SUCCESS);
        User user = userDAO.getUser(1);
        Map<String, Object> payload = new HashMap<>();
        payload.put("user", user);
        res.setPayload(payload);
        return res;
    }

    @Override
    public Response userRegister(UserRegister userRegister) {
        Response res = new Response();
        User user = new User();
        user.setName(userRegister.getName());
        user.setNumber(userRegister.getNumber());
        user.setPhone(userRegister.getPhone());
        user.setPassword(userRegister.getPassword());
        Long time = new Date().getTime();
        user.setCreateTime(time);
        user.setUpdateTime(time);
        userDAO.userRegister(user);

        res.setMessage(Message.SUCCESS);
        return res;
    }
}
