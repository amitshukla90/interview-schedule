package com.example.interviewscheduler.model.request;

import com.example.interviewscheduler.model.InterviewSlotDetail;
import lombok.Data;

import java.util.List;

@Data
public class InterviewAvailabilityRequest {
    private String startDate;
    private String endDate;
    private List<InterviewSlotDetail> interviewSlotDetails;

}
