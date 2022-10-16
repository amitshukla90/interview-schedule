package com.example.interviewscheduler.model;

import com.example.interviewscheduler.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String userName;
    private String name;
    private String password;
    private UserRole userRole;

}
