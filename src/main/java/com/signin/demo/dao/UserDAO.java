package com.signin.demo.dao;

import com.signin.demo.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserDAO {

    @Select("select * from user where number = #{number}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "number", column = "number"),
            @Result(property = "role", column = "roles")
    })
    public User getUserByNumber(String number);

    @Update("UPDATE user " +
            "SET access_token = #{accessToken}, expire_time = #{expireTime}, update_time = #{now} " +
            "WHERE number = #{number}")
    public Integer updateAccessToken(@Param("accessToken") String accessToken,
                                     @Param("number") String number,
                                     @Param("expireTime") Long expireTime,
                                     @Param("now") Long now);




    @Insert("insert into user (name, phone, number, password, roles, create_time, update_time) " +
        "values (#{name}, #{phone}, #{number}, #{password}, #{role}, #{createTime}, #{updateTime} )")
    public Integer userRegister(User user);
}
