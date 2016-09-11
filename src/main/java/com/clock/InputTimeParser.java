package com.clock;

import java.util.regex.Pattern;

public class InputTimeParser implements TimeParser {

    private static final Pattern TIME_PARSE_REGEX_PATTERN = Pattern.compile(":");

    @Override
    public String[] parseTime(String time) {
        return TIME_PARSE_REGEX_PATTERN.split(time);
    }

    @Override
    public int parseTimeField(String timeField) {
        return Integer.parseInt(timeField);
    }
}
