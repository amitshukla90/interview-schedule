package com.example.interviewscheduler.controller;

import com.example.interviewscheduler.model.request.CheckInterviewAvailabilityRequest;
import com.example.interviewscheduler.model.request.InterviewAvailabilityRequest;
import com.example.interviewscheduler.service.InterviewSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor
public class InterviewSchedulerController {
    private final InterviewSchedulerService interviewSchedulerService;

    /*
      This call will handle adding schedule for the interviewers
     */
    @PostMapping("/add-schedule")
    public ResponseEntity addInterviewSchedule(@RequestHeader("user-name") String username,
                                               @RequestBody InterviewAvailabilityRequest request) {
        return new ResponseEntity(interviewSchedulerService.addInterviewSchedule(username, request),
                HttpStatus.CREATED);
    }

    /*
      This call will help for booking the schedule for candidate, Candidate will give preference slots into input
     */
    @PostMapping("/book-schedule")
    public ResponseEntity bookInterviewSchedule(@RequestBody CheckInterviewAvailabilityRequest request) {
        return new ResponseEntity(interviewSchedulerService.bookInterviewSchedule(request),
                HttpStatus.CREATED);
    }

}
