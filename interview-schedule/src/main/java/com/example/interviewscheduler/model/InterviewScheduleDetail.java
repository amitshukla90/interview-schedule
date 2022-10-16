package com.example.interviewscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewScheduleDetail {
    private String interviewScheduleId;
    private List<InterviewSlotDetail> interviewSlotDetails;
}
