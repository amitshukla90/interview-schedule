package com.example.interviewscheduler.service.impl;

import com.example.interviewscheduler.enums.InterviewSchedulerError;
import com.example.interviewscheduler.exception.UserException;
import com.example.interviewscheduler.model.User;
import com.example.interviewscheduler.model.response.UserResponse;
import com.example.interviewscheduler.repository.UserRepository;
import com.example.interviewscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(User user) {
        if (userRepository.isUserNameAlreadyExits(user.getUserName())) {
            log.info("Username is already exits!! : {}", user.getUserName());
            throw new UserException(InterviewSchedulerError.USERNAME_ALREADY_EXITS);
        } else {
            log.info("User :{} is registered!!", user.getUserName());
            return registerUser(user);
        }
    }
}
