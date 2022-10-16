package com.example.interviewscheduler.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterviewSchedulerError {

    USERNAME_ALREADY_EXITS("100", "Username is already exits"),
    USER_IS_NOT_REGISTERED("101", "User is not registered!!"),
    NO_INTERVIEW_SLOT_AVAILABLE("102", "No Interview slot is available for the date!!"),
    INTERVIEW_SLOT_IS_ALREADY_PRESENT("103", "Interview Slot for the date range is already present");

    private String errorCode;
    private String errorMessage;
}
