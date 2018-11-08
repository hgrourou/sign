package com.signin.demo.dao;

import com.signin.demo.entity.Sign;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface SignDAO {



    @Insert("insert into sign(number, name, insert_time) values (#{number}, #{name}, #{insertTime})")
    public Integer userSign(Sign sign);

    @Select("select count(*) from sign where insert_time > #{startTime} and insert_time < #{endTime} and number = #{number} ")
    public Integer getCountByStamp(
            @Param("number") String number,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
        );



    @Select("select * from sign where insert_time > #{startTime} and insert_time < #{endTime} and number = #{number} ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "number", column = "number"),
            @Result(property = "insertTime", column = "insert_time"),
            @Result(property = "name", column = "name")
    })
    public ArrayList<Sign> getUserSIgnList(
            @Param("number") String number,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
    );
}
