package com.signin.demo.dao;

import com.signin.demo.entity.Sign;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SignDAO {



    @Insert("insert into sign(number, name, insert_time) values (#{number}, #{name}, #{insertTime})")
    public Integer userSign(Sign sign);

    @Select("select count(*) from sign where insert_time > #{startTime} and insert_time < #{endTime} and number = #{number} ")
    public Integer getCountByStamp(
            @Param("number") String number,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
        );
}
