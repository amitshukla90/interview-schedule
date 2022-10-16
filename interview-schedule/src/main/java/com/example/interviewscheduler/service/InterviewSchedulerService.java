package com.example.interviewscheduler.service;

import com.example.interviewscheduler.model.InterviewScheduleDetail;
import com.example.interviewscheduler.model.request.CheckInterviewAvailabilityRequest;
import com.example.interviewscheduler.model.request.InterviewAvailabilityRequest;
import com.example.interviewscheduler.model.response.CheckInterviewAvailabilityResponse;
import com.example.interviewscheduler.model.response.InterviewAvailabilityResponse;

public interface InterviewSchedulerService {
    InterviewAvailabilityResponse addInterviewSchedule(String username, InterviewAvailabilityRequest request);

    CheckInterviewAvailabilityResponse bookInterviewSchedule(CheckInterviewAvailabilityRequest request);
}
