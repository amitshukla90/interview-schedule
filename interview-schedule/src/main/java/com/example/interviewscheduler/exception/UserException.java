package com.example.interviewscheduler.exception;

import com.example.interviewscheduler.enums.InterviewSchedulerError;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserException extends RuntimeException {
    protected String message;
    private InterviewSchedulerError error;

    public UserException(InterviewSchedulerError error) {
        super(error.getErrorMessage());
        this.error = error;
    }
}
