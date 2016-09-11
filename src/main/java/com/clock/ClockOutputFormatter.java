package com.clock;

public interface ClockOutputFormatter {
    String formatTime(String seconds, String hours, String minutes);

    String formatHours(int hour);

    String formatMinutes(int minutes);

    String formatSeconds(int seconds);
}
