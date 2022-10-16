package com.example.interviewscheduler.model.response;

import com.example.interviewscheduler.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String userId;
    private String name;
    private UserRole userRole;
}
