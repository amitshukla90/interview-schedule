package com.example.interviewscheduler.service;

import com.example.interviewscheduler.model.User;
import com.example.interviewscheduler.model.response.UserResponse;

public interface UserService {
    UserResponse registerUser(User user);
}
