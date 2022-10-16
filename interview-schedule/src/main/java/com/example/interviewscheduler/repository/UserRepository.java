package com.example.interviewscheduler.repository;


import com.example.interviewscheduler.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    public static ConcurrentHashMap<String, User> userDetails = new ConcurrentHashMap<>();

    public boolean isUserNameAlreadyExits(String userName) {
        return userDetails.containsKey(userName);
    }

    public User saveUser(User user) {
        userDetails.put(user.getUserName(), user);
        return user;
    }
}
