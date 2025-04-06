package com.example.accountbook.service;

import com.example.accountbook.entity.User;
import com.example.accountbook.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> login (String username,String password){
        Map<String, Object> response = new HashMap<>();

        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            response.put("success", true);
        }else {
            response.put("success", false);
        }
        return response;
    }
}
