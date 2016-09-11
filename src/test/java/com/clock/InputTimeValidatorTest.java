package com.clock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class InputTimeValidatorTest {
    @Mock
    private TimeParser timeParser;

    @InjectMocks
    private InputTimeValidator timeValidator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        timeValidator = null;
    }

    public Collection<Object[]> validTimes() {
        return Arrays.asList(new Object[][]{
                        {"00:00:00", new String[]{"00", "00", "00"}, new Integer[]{0, 0, 0}},
                        {"12:34:56", new String[]{"12", "34", "56"}, new Integer[]{12, 34, 56}},
                        {"24:00:00", new String[]{"24", "00", "00"}, new Integer[]{24, 0, 0}}
                }
        );
    }

    @Test
    @Parameters(method = "validTimes")
    public void checkValidTime(String input, String[] parsedTime, Integer[] expected) throws Exception {
        when(timeParser.parseTime(input)).thenReturn(parsedTime);
        assertArrayEquals("Array elements donot match", expected, timeValidator.getValidatedTime(input));
    }

    public Collection<Object[]> invalidTimes() {
        return Arrays.asList(new Object[][]{
                        {null, null},
                        {"", new String[]{""}},
                        {"24:00", new String[]{"24", "00"}},
                        {"24:00:01", new String[]{"24", "00", "01"}}
                }
        );
    }

    @Test(expected = RuntimeException.class)
    @Parameters(method = "invalidTimes")
    public void checkInvalidTime(String input, String[] expected) throws Exception {
        when(timeParser.parseTime(input)).thenReturn(expected);
        assertArrayEquals("Array elements do not match", expected, timeValidator.getValidatedTime(input));
    }

}
