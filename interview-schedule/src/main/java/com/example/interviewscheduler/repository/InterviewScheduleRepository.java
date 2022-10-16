package com.example.interviewscheduler.repository;

import com.example.interviewscheduler.enums.WeekDay;
import com.example.interviewscheduler.model.InterviewMetaData;
import com.example.interviewscheduler.model.InterviewSlotDetail;
import com.example.interviewscheduler.model.InterviewSlotTiming;
import com.example.interviewscheduler.model.request.CheckInterviewAvailabilityRequest;
import com.example.interviewscheduler.model.request.InterviewAvailabilityRequest;
import com.example.interviewscheduler.model.response.InterviewAvailabilityResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.interviewscheduler.util.InterviewSchedulerUtil.getInterviewId;

@Repository
public class InterviewScheduleRepository {
    public static ConcurrentHashMap<String, List<InterviewMetaData>> interviewSchDetailDate =
            new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> interviewSchDetail =
            new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Map<String, Map<WeekDay, Set<String>>>> interviewerBookedDetails =
            new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Map<String, Set<String>>> candidateBookedDetails =
            new ConcurrentHashMap<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");

    public InterviewAvailabilityResponse saveInterviewSchedule(String userName,
                                                               InterviewAvailabilityRequest availabilityRequest) {
        List<InterviewMetaData> interviewMetaDataList = new ArrayList<>();
        Map<WeekDay, List<InterviewSlotTiming>> interviewScheduleDetails = new HashMap<>();
        availabilityRequest.getInterviewSlotDetails().stream().forEach(interviewSlotDetail -> {
            interviewScheduleDetails.put(interviewSlotDetail.getWeekDay(),
                    interviewSlotDetail.getInterviewSlotTimings());
        });
        if (interviewSchDetailDate.containsKey(userName)) {
            interviewMetaDataList = interviewSchDetailDate.get(userName);
        }
        InterviewMetaData interviewMetaData =
                InterviewMetaData.builder().interviewSlots(interviewScheduleDetails).interviewScheduleId(getInterviewId())
                        .endDate(availabilityRequest.getEndDate()).startDate(availabilityRequest.getStartDate()).build();
        interviewMetaDataList.add(interviewMetaData);
        interviewSchDetailDate.put(userName, interviewMetaDataList);
        interviewSchDetail.put(userName, interviewScheduleDetails);
        InterviewAvailabilityResponse interviewScheduleDetail =
                InterviewAvailabilityResponse.builder().interviewScheduleId(getInterviewId())
                        .endDate(availabilityRequest.getEndDate()).startDate(availabilityRequest.getStartDate())
                        .interviewSlotDetails(availabilityRequest.getInterviewSlotDetails()).build();
        return interviewScheduleDetail;
    }

    @SneakyThrows
    public ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> isScheduleAvailFromDate(String fromDate) {
        ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> slots = new ConcurrentHashMap<>();
        Date fromDateVal = simpleDateFormat.parse(fromDate);
        interviewSchDetailDate.forEach((userName, interviewMetaData) ->
                interviewMetaData.stream().forEach(interviewMeta -> {
                    try {
                        if (simpleDateFormat.parse(interviewMeta.getStartDate()).compareTo(fromDateVal) <= 0 &&
                                simpleDateFormat.parse(interviewMeta.getEndDate()).compareTo(fromDateVal) >= 0) {
                            slots.put(userName, interviewMeta.getInterviewSlots());
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }));
        return slots;
    }

    public boolean isSlotIsAlreadyBooked(String userName, String date, WeekDay weekDay, String startTime) {
        boolean isBooked = false;
        if (interviewerBookedDetails.containsKey(userName) && interviewerBookedDetails.get(userName).containsKey(date)
                && interviewerBookedDetails.get(userName).get(date).containsKey(weekDay) &&
                interviewerBookedDetails.get(userName).get(date).get(weekDay).contains(startTime)) {
            isBooked = true;
        }
        return isBooked;
    }

    public ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> findSlotForBooking(
            CheckInterviewAvailabilityRequest request,
            ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> slotsDetails) {
        ConcurrentHashMap<String, Map<WeekDay, List<InterviewSlotTiming>>> out = new ConcurrentHashMap<>();
        List<InterviewSlotDetail> interviewSlotDetails = request.getInterviewSlotDetails();
        slotsDetails.keys().asIterator().forEachRemaining(userName ->
                interviewSlotDetails.stream().forEach(interviewSlotDetail ->
                        interviewSlotDetail.getInterviewSlotTimings().stream().forEach(interviewSlotTiming -> {
                            if (!isSlotIsAlreadyBooked(userName, request.getFromDate(), interviewSlotDetail.getWeekDay(),
                                    interviewSlotTiming.getStartTime())) {
                                if (slotsDetails.get(userName).get(interviewSlotDetail.getWeekDay())
                                        .contains(interviewSlotTiming.getStartTime())) {
                                    Map<WeekDay, List<InterviewSlotTiming>> weekDaySlotTimings = new HashMap<>();
                                    List<InterviewSlotTiming> interviewSlotTimings = new ArrayList<>();
                                    if (out.containsKey(userName)) {
                                        weekDaySlotTimings = out.get(userName);
                                    }
                                    if (out.containsKey(userName)) {
                                        weekDaySlotTimings = out.get(userName);
                                    }
                                    if (weekDaySlotTimings.containsKey(interviewSlotDetail.getWeekDay())) {
                                        interviewSlotTimings = weekDaySlotTimings.get(interviewSlotDetail.getWeekDay());
                                    }
                                    interviewSlotTimings.add(new InterviewSlotTiming(interviewSlotTiming.getStartTime(),
                                            interviewSlotTiming.getEndTime()));
                                    weekDaySlotTimings.put(interviewSlotDetail.getWeekDay(), interviewSlotTimings);
                                    out.put(userName, weekDaySlotTimings);

                                }
                            }
                        })));
        return out;
    }
}
