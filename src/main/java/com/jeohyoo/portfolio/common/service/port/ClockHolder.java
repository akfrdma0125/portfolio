package com.jeohyoo.portfolio.common.service.port;

public interface ClockHolder {
    long millis();
    String date();
    String dateTime(Long millis);
}
