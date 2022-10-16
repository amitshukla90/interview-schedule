package com.example.interviewscheduler.model;

import com.example.interviewscheduler.enums.WeekDay;
import lombok.Data;

import java.util.List;

@Data
public class InterviewSlotDetail {
    private WeekDay weekDay;
    private List<InterviewSlotTiming> interviewSlotTimings;
}
