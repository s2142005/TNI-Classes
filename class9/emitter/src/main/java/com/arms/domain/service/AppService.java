package com.arms.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arms.domain.repository.UserRepository;

@Service
public class AppService {

    @Autowired
    UserRepository userRepository;
}
