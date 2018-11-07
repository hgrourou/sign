package com.signin.demo.service.impl;

import com.signin.demo.config.Message;
import com.signin.demo.config.Response;
import com.signin.demo.dao.SignDAO;
import com.signin.demo.dao.UserDAO;
import com.signin.demo.entity.Sign;
import com.signin.demo.entity.User;
import com.signin.demo.service.SignService;
import com.signin.demo.util.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignServiceImpl implements SignService {

    @Autowired(required = false)
    private UserDAO userDAO;

    @Autowired(required = false)
    private SignDAO signDAO;

    @Override
    public Response userSign(String number) {
        Response res = new Response();
        User user = userDAO.getUserByNumber(number);
        if (user == null) {
            res.setMessage(Message.ERROR);
            return res;
        }
        Long startTimestamp = TimeHandler.getStartTime();
        Long endTimestamp = TimeHandler.getEndTime();
        Integer count = signDAO.getCountByStamp(number, startTimestamp, endTimestamp);
        if (count > 0) {
            res.setMessage(Message.HAS_SIGN);
            return res;
        }

        int hour = new Date().getHours();
        if (hour > 0) {
            Sign sign = new Sign();
            sign.setName(user.getName());
            sign.setNumber(number);
            final Long time = new Date().getTime();
            sign.setInsertTime(time);
            try {
                signDAO.userSign(sign);
            } catch (Exception e) {
                res.setMessage(Message.ERROR);
                return res;
            }
        }
        res.setMessage(Message.SUCCESS);
        return res;
    }
}
