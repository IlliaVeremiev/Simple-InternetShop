package veil.internetshop.simple.services.impl;

import veil.internetshop.simple.services.TimeService;

import java.sql.Timestamp;
import java.util.Date;

public class DefaultTimeService implements TimeService{

    @Override
    public Timestamp getCurrentTime(){
        return new Timestamp(new Date().getTime());
    }
}
