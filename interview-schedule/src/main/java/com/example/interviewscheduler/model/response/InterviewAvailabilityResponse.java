package com.example.interviewscheduler.model.response;

import com.example.interviewscheduler.model.InterviewSlotDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewAvailabilityResponse {
    private String interviewScheduleId;
    private String startDate;
    private String endDate;
    private List<InterviewSlotDetail> interviewSlotDetails;
}
