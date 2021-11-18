package by.it_academy.food_control.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateConversion {

    public Long getDateInLong(LocalDateTime localDateTime){

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


}
