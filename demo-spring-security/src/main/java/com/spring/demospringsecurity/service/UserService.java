package com.spring.demospringsecurity.service;


import com.spring.demospringsecurity.model.UserInfo;
import com.spring.demospringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public UserInfo saveUser(UserInfo user){
        user.setPassword(encoder.encode(user.getPassword()));
       return userRepo.save(user);
    }
}
