package com.spring.demospringsecurity.controller;


import com.spring.demospringsecurity.model.AuthRequest;
import com.spring.demospringsecurity.model.UserInfo;
import com.spring.demospringsecurity.service.JwtService;
import com.spring.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService uService;

    @Autowired
    private JwtService jService;

    @Autowired
    private AuthenticationManager authManager;



    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/greet")
    @PreAuthorize("hasRole('ADMIN')")
    public String greet(){
        return "Hello";
    }

    @GetMapping("/welcome")
    @PreAuthorize("hasRole('USER')")
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/add")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo user){
        UserInfo savedUser = uService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return jService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("user not found");
        }

    }
}
