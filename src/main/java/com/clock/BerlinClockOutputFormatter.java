package com.clock;

import java.util.Arrays;

public class BerlinClockOutputFormatter implements ClockOutputFormatter {
    private static final char LAMP_OFF = 'O';
    private static final char LAMP_YELLOW = 'Y';
    private static final char LAMP_RED = 'R';
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int TIME_DIVISOR = 5;
    private static final int COMMON_LAMP_COUNT = 4;
    private static final int MINUTE_FIRST_ROW_LAMP_COUNT = 11;

    @Override
    public String formatTime(String seconds, String hours, String minutes) {
        return String.join(LINE_SEPARATOR, seconds, hours, minutes);
    }

    @Override
    public String formatHours(int hour) {
        int enabledLampCountRow1 = hour / TIME_DIVISOR;
        int enabledLampCountRow2 = hour % TIME_DIVISOR;
        char[] hourRow1 = getLampRow(COMMON_LAMP_COUNT);
        char[] hourRow2 = getLampRow(COMMON_LAMP_COUNT);

        for (int i = 0; i < enabledLampCountRow1; i++) {
            hourRow1[i] = LAMP_RED;
        }

        for (int i = 0; i < enabledLampCountRow2; i++) {
            hourRow2[i] = LAMP_RED;
        }


        return String.join(LINE_SEPARATOR, String.valueOf(hourRow1), String.valueOf(hourRow2));

    }

    @Override
    public String formatMinutes(int minutes) {
        int enabledLampCountRow1 = minutes / TIME_DIVISOR;
        int enabledLampCountRow2 = minutes % TIME_DIVISOR;
        char[] minuteRow1 = getLampRow(MINUTE_FIRST_ROW_LAMP_COUNT);
        char[] minuteRow2 = getLampRow(COMMON_LAMP_COUNT);

        for (int i = 0; i < enabledLampCountRow1; i++) {
            if ((i + 1) % 3 == 0) {
                minuteRow1[i] = LAMP_RED;
            } else {
                minuteRow1[i] = LAMP_YELLOW;
            }
        }

        for (int i = 0; i < enabledLampCountRow2; i++) {
            minuteRow2[i] = LAMP_YELLOW;
        }

        return String.join(LINE_SEPARATOR, String.valueOf(minuteRow1), String.valueOf(minuteRow2));
    }

    @Override
    public String formatSeconds(int seconds) {
        return String.valueOf(seconds % 2 == 0 ? LAMP_YELLOW : LAMP_OFF);
    }

    private char[] getLampRow(int length) {
        char[] lamps = new char[length];
        Arrays.fill(lamps, LAMP_OFF);
        return lamps;
    }
}