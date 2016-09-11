package com.clock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BerlinClockOutputFormatterTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private ClockOutputFormatter clockOutputFormatter;

    @Before
    public void init() {
        clockOutputFormatter = new BerlinClockOutputFormatter();
    }

    @After
    public void destroy() {
        clockOutputFormatter = null;
    }

    public Collection<Object[]> hours() {
        return Arrays.asList(new Object[][]{
                {0, String.join(LINE_SEPARATOR, "OOOO", "OOOO")},
                {1, String.join(LINE_SEPARATOR, "OOOO", "ROOO")},
                {4, String.join(LINE_SEPARATOR, "OOOO", "RRRR")},
                {5, String.join(LINE_SEPARATOR, "ROOO", "OOOO")},
                {6, String.join(LINE_SEPARATOR, "ROOO", "ROOO")},
                {15, String.join(LINE_SEPARATOR, "RRRO", "OOOO")},
                {20, String.join(LINE_SEPARATOR, "RRRR", "OOOO")},
                {23, String.join(LINE_SEPARATOR, "RRRR", "RRRO")},
                {24, String.join(LINE_SEPARATOR, "RRRR", "RRRR")}
        });
    }

    @Test
    @Parameters(method = "hours")
    public void validateHoursOutput(int input, String expectedOutput) {
        assertEquals(clockOutputFormatter.formatHours(input), expectedOutput);
    }

    public Collection<Object[]> minutes() {
        return Arrays.asList(new Object[][]{
                {0, String.join(LINE_SEPARATOR, "OOOOOOOOOOO", "OOOO")},
                {1, String.join(LINE_SEPARATOR, "OOOOOOOOOOO", "YOOO")},
                {4, String.join(LINE_SEPARATOR, "OOOOOOOOOOO", "YYYY")},
                {5, String.join(LINE_SEPARATOR, "YOOOOOOOOOO", "OOOO")},
                {6, String.join(LINE_SEPARATOR, "YOOOOOOOOOO", "YOOO")},
                {15, String.join(LINE_SEPARATOR, "YYROOOOOOOO", "OOOO")},
                {26, String.join(LINE_SEPARATOR, "YYRYYOOOOOO", "YOOO")},
                {55, String.join(LINE_SEPARATOR, "YYRYYRYYRYY", "OOOO")},
                {59, String.join(LINE_SEPARATOR, "YYRYYRYYRYY", "YYYY")}
        });
    }

    @Test
    @Parameters(method = "minutes")
    public void validateMinutesOutput(int input, String expectedOutput) {
        assertEquals(clockOutputFormatter.formatMinutes(input), expectedOutput);
    }

    public Collection<Object[]> seconds() {
        return Arrays.asList(new Object[][]{
                {0, "Y"},
                {1, "O"},
                {24, "Y"},
                {59, "O"}
        });
    }

    @Test
    @Parameters(method = "seconds")
    public void validateSecondsOutput(int input, String expectedOutput) {
        assertEquals(clockOutputFormatter.formatSeconds(input), expectedOutput);
    }
}
