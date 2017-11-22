package com.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Created by liujq on 17-8-31.
 */
public class MyDateUtils {


    public static void main(String[] args){


        LocalDate parse = LocalDate.parse("2017-08-23");
        System.out.println(parse);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(formatter);
        System.out.println("-----------" + format);

        Instant instant = Instant.now().plus(Duration.ofMinutes(10));
        System.out.println(" date from instant: "+Date.from(instant));

        LocalDateTime dateTime = LocalDateTime.now();
        long time = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(time);
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));

        Instant instant1 = Instant.ofEpochMilli(1510642458687L);
        Instant instant2 = Instant.ofEpochMilli(1510557602363l);
        System.out.println(instant1.atZone(ZoneId.systemDefault()));

        System.out.println(instant2.atZone(ZoneId.systemDefault()));
        System.out.println(LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()));


        String text = "exceptionAlert.html";
        System.out.println(text.substring(text.length()-5).equals(".html"));
    }


}
