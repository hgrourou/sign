package com.signin.demo.dao;

import com.signin.demo.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserDAO {

    @Select("select * from user where id = 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "number", column = "number")
    })
    public User getUser(Integer id);
}
