package com.example.interviewscheduler.model;

import com.example.interviewscheduler.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewMetaData {
    private String startDate;
    private String endDate;
    private String interviewScheduleId;
    private Map<WeekDay, List<InterviewSlotTiming>> interviewSlots;

}
