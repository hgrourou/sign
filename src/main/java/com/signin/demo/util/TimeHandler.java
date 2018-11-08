package com.signin.demo.util;

import java.util.Date;

public class TimeHandler {

    /**
     * Get start time of a day
     *
     * @return
     */

    public static Long getDayStartTime(){
        Date todayStart = new Date();
        todayStart.setHours(0);
        todayStart.setMinutes(0);
        todayStart.setSeconds(0);
        return todayStart.getTime();
    }

    /**
     * Get end time of a day
     *
     * @return
     */
    public static Long getDayEndTime(){
        Date todayStart = new Date();
        todayStart.setHours(23);
        todayStart.setMinutes(59);
        todayStart.setSeconds(59);
        return todayStart.getTime();
    }

    public static Long getCurrentMonthStartTime(){
        Date todayStart = new Date();
        todayStart.setDate(1);
        todayStart.setHours(0);
        todayStart.setMinutes(0);
        todayStart.setSeconds(0);
        return todayStart.getTime();
    }

    public static Long getCurrentMonthEndTime(){
        Date todayStart = new Date();
        todayStart.setMonth(todayStart.getMonth() + 1);
        todayStart.setDate(0);
        todayStart.setHours(0);
        todayStart.setMinutes(0);
        todayStart.setSeconds(0);
        return todayStart.getTime();
    }

}
