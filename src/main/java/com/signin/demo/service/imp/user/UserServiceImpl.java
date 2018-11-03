package com.signin.demo.service.imp.user;

import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.dao.UserDAO;
import com.signin.demo.entity.User;
import com.signin.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public Response userLogin() {
        Response res = new Response();
        res.setMessage(Message.SUCCESS);
        User user = userDAO.getUser(1);
        Map<String, Object> payload = new HashMap<>();
        payload.put("user", user);
        res.setPayload(payload);
        return res;
    }
}
