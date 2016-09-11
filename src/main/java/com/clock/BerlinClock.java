package com.clock;

public class BerlinClock implements TimeConverter {

    private static final int HOURS_INDEX = 0;
    private static final int MINUTES_INDEX = 1;
    private static final int SECONDS_INDEX = 2;
    private static final BerlinClock INSTANCE = new BerlinClock(
            new InputTimeValidator(new InputTimeParser()), new BerlinClockOutputFormatter()
    );

    private TimeValidator timeValidator;
    private ClockOutputFormatter clockOutputFormatter;

    private BerlinClock(TimeValidator timeValidator, ClockOutputFormatter clockOutputFormatter) {
        this.timeValidator = timeValidator;
        this.clockOutputFormatter = clockOutputFormatter;
    }

    public static BerlinClock getInstance() {
        return INSTANCE;
    }

    public String convertTime(String givenTime) {
        Integer[] parsedTime = timeValidator.getValidatedTime(givenTime);

        return clockOutputFormatter.formatTime(
                clockOutputFormatter.formatSeconds(parsedTime[SECONDS_INDEX]), clockOutputFormatter.formatHours(parsedTime[HOURS_INDEX]), clockOutputFormatter.formatMinutes(parsedTime[MINUTES_INDEX])
        );
    }
}