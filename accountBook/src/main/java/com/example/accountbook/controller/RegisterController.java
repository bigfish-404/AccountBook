package com.example.accountbook.controller;

import com.example.accountbook.entity.User;
import com.example.accountbook.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user){

        Map<String, Object> response = new HashMap<>();
        boolean success = registerService.registerUser(user);

        if (success){
            response.put("success",true);
        } else {
            response.put("success", false);
            response.put("message", "ユーザー名は既に使用されています！");
        }

        return response;
    }
}
