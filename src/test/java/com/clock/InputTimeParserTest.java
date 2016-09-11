package com.clock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class InputTimeParserTest {
    private TimeParser timeParser;

    @Before
    public void setUp() {
        timeParser = new InputTimeParser();
    }

    @After
    public void destroy() {
        timeParser = null;
    }

    public Object timeValues() {
        return Arrays.asList(new Object[][]{
                {"12:34:56", 3},
                {"00:00:00", 3}
        });
    }

    @Test
    @Parameters(method = "timeValues")
    public void checkParseTime(String input, int expected) {
        assertEquals("The time parsing failed", expected, timeParser.parseTime(input).length);
    }

    public Object timeFieldValues() {
        return Arrays.asList(new Object[][]{
                {"56", 56},
                {"00", 0}
        });
    }

    @Test
    @Parameters(method = "timeFieldValues")
    public void checkParseTimeField(String input, int expected) {
        assertEquals("The time field parsing failed", expected, timeParser.parseTimeField(input));
    }
}
