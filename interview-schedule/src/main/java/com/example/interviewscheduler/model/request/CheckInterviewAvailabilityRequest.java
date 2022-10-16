package com.example.interviewscheduler.model.request;

import com.example.interviewscheduler.model.InterviewSlotDetail;
import lombok.Data;

import java.util.List;

@Data
public class CheckInterviewAvailabilityRequest {
    private String candidateName;
    private String fromDate;
    private String endDate;
    private List<InterviewSlotDetail> interviewSlotDetails;
}
