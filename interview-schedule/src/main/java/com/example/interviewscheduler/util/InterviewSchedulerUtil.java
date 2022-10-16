package com.example.interviewscheduler.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class InterviewSchedulerUtil {
    public static String getInterviewId(){
        return ("INTR" + RandomStringUtils.randomAlphabetic(3) +
                Long.toString(System.currentTimeMillis(), 36)).toUpperCase();
    }
}
