package com.clock;

public interface TimeParser {
    String[] parseTime(String time);

    int parseTimeField(String timeField);
}
