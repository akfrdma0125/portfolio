package com.jeohyoo.portfolio.common.domain.port;

public interface ClockHolder {
    long millis();
    String date();
    String dateTime(Long millis);
}
