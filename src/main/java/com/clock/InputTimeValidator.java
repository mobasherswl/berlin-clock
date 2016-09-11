package com.clock;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputTimeValidator implements TimeValidator {

    private static final Pattern REGEX_TIME_PATTERN = Pattern.compile("([0-1][0-9]|[2][0-3])(:[0-5][0-9]){2}|24:00:00");
    private static final String ERROR_MSG_NULL_EMPTY_VALUE = "%s value is null or empty";
    private static final String ERROR_MSG_VALUE = ": {%s}";
    private static final String ERROR_MSG_INVALID_VALUE = "%s value is invalid or in invalid format";
    private static final String VAUE_TIME = "Time";
    private TimeParser timeParser;

    public InputTimeValidator(TimeParser timeParser) {
        this.timeParser = timeParser;
    }

    private void isNotNull(String value, String message) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException(String.join("", message, encloseValue(value)));
        }
    }

    private String getEmptyNullMessage(String field) {
        return String.format(ERROR_MSG_NULL_EMPTY_VALUE, field);
    }

    private String encloseValue(String value) {
        return String.format(ERROR_MSG_VALUE, value);
    }

    private String getInvalidFormatMessage(String field) {
        return String.format(ERROR_MSG_INVALID_VALUE, field);
    }

    @Override
    public Integer[] getValidatedTime(String time) {
        isNotNull(time, getEmptyNullMessage(VAUE_TIME));
        if (!REGEX_TIME_PATTERN.matcher(time).matches()) {
            throw new RuntimeException(getInvalidFormatMessage(VAUE_TIME).concat(encloseValue(time)));
        }

        return Arrays.stream(timeParser.parseTime(time)).
                map(s -> Integer.parseInt(s)).collect(Collectors.toList()).toArray(new Integer[0]);

    }

}
