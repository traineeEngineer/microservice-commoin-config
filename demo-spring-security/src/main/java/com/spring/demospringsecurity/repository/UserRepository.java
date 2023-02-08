package com.spring.demospringsecurity.repository;

import com.spring.demospringsecurity.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByUsername(String username);



}
