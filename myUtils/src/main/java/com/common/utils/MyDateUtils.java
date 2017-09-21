package com.common.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

/**
 * Created by liujq on 17-8-31.
 */
public class MyDateUtils {


    public static void main(String[] args){
        LocalDate parse = LocalDate.parse("2017-08-23");
        System.out.println(parse);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = date.format(formatter);
        System.out.println("-----------" + format);

        Instant instant = Instant.now().plus(Duration.ofMinutes(10));
    }


}
