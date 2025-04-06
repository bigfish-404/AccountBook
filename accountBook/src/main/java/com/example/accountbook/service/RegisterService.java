package com.example.accountbook.service;

import com.example.accountbook.entity.User;
import com.example.accountbook.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    public boolean registerUser(User user){

        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            return false;
        }

        userMapper.registerUser(user);
        return true;
    }
}
