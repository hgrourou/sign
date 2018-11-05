package com.signin.demo.dao.secruity;

import com.signin.demo.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AccountDao {

    @Select("SELECT * FROM account WHERE username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "description", column = "description"),
            @Result(property = "ctime", column = "ctime"),
            @Result(property = "utime", column = "utime"),
            @Result(property = "locked", column = "locked"),
            @Result(property = "accessToken", column = "access_token"),
            @Result(property = "role", column = "role"),
            @Result(property = "expireTime", column = "expire_time")})
    public User getAccountByUsername(String username);
}
