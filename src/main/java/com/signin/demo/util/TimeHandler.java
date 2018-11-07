package com.signin.demo.util;

import java.util.Date;

public class TimeHandler {

    /**
     * Get start time of a day
     *
     * @return
     */

    public static Long getStartTime(){
//        Calendar todayStart = Calendar.getInstance();
//        todayStart.set(Calendar.HOUR, 0);
//        todayStart.set(Calendar.MINUTE, 0);
//        todayStart.set(Calendar.SECOND, 0);
//        todayStart.set(Calendar.MILLISECOND, 0);
//        return todayStart.getTime().getTime();

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
    public static Long getEndTime(){
//        Calendar todayEnd = Calendar.getInstance();
//        todayEnd.set(Calendar.HOUR, 23);
//        todayEnd.set(Calendar.MINUTE, 59);
//        todayEnd.set(Calendar.SECOND, 59);
//        todayEnd.set(Calendar.MILLISECOND, 999);
//        return todayEnd.getTime().getTime();

        Date todayStart = new Date();
        todayStart.setHours(23);
        todayStart.setMinutes(59);
        todayStart.setSeconds(59);
        return todayStart.getTime();
    }

}
