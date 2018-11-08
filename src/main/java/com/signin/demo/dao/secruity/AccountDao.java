package com.signin.demo.dao.secruity;

import com.signin.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AccountDao {

    @Select("SELECT * FROM user WHERE number = #{number}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "number", column = "number"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "accessToken", column = "access_token"),
            @Result(property = "role", column = "roles"),
            @Result(property = "expireTime", column = "expires")})
    public User getAccountByUsername(@Param("number") String number);
}
