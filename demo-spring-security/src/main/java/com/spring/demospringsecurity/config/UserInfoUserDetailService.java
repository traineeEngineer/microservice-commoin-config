package com.spring.demospringsecurity.config;

import com.spring.demospringsecurity.model.UserInfo;
import com.spring.demospringsecurity.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> byUsername = userRepo.findByUsername(username);
        return byUsername.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
