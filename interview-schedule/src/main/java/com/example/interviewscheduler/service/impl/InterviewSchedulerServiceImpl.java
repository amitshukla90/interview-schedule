package com.example.interviewscheduler.service.impl;

import com.example.interviewscheduler.enums.WeekDay;
import com.example.interviewscheduler.exception.InterviewScheduleException;
import com.example.interviewscheduler.exception.UserException;
import com.example.interviewscheduler.model.InterviewSlotTiming;
import com.example.interviewscheduler.model.request.CheckInterviewAvailabilityRequest;
import com.example.interviewscheduler.model.request.InterviewAvailabilityRequest;
import com.example.interviewscheduler.model.response.CheckInterviewAvailabilityResponse;
import com.example.interviewscheduler.model.response.InterviewAvailabilityResponse;
import com.example.interviewscheduler.repository.InterviewScheduleRepository;
import com.example.interviewscheduler.repository.UserRepository;
import com.example.interviewscheduler.service.InterviewSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.interviewscheduler.enums.InterviewSchedulerError.NO_INTERVIEW_SLOT_AVAILABLE;
import static com.example.interviewscheduler.enums.InterviewSchedulerError.USER_IS_NOT_REGISTERED;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterviewSchedulerServiceImpl implements InterviewSchedulerService {
    private final UserRepository userRepository;
    private final InterviewScheduleRepository interviewScheduleRepository;

    @Override
    public InterviewAvailabilityResponse addInterviewSchedule(String userName, InterviewAvailabilityRequest request) {
        log.info("Request from user: {} to create interview schedule: {}", userName, request);
        if (!userRepository.isUserNameAlreadyExits(userName)) {
            throw new UserException(USER_IS_NOT_REGISTERED);
        }
        return interviewScheduleRepository.saveInterviewSchedule(userName, request);
    }

    @Override
    public CheckInterviewAvailabilityResponse bookInterviewSchedule(CheckInterviewAvailabilityRequest request) {
        CheckInterviewAvailabilityResponse response = new CheckInterviewAvailabilityResponse();
        ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> slotsDetails =
                interviewScheduleRepository.isScheduleAvailFromDate(request.getFromDate());
        if (CollectionUtils.isEmpty(slotsDetails)) {
            throw new InterviewScheduleException(NO_INTERVIEW_SLOT_AVAILABLE);
        } else {
            ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> suggestedSlots =
                    interviewScheduleRepository.findSlotForBooking(request, slotsDetails);
            response.setSuggestedSlots(suggestedSlots);

        }

        return response;
    }
}
