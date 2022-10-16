package com.example.interviewscheduler.model.response;

import com.example.interviewscheduler.enums.WeekDay;
import com.example.interviewscheduler.model.InterviewSlotTiming;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class CheckInterviewAvailabilityResponse {
    private ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> suggestedSlots;
}
