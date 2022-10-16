package com.example.interviewscheduler.controller;

import com.example.interviewscheduler.model.User;
import com.example.interviewscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /*
     This call will handle the interviewer registeration for schedule-service
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        return new ResponseEntity(userService.registerUser(user), HttpStatus.OK);
    }
}
