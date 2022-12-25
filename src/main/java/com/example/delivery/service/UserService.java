package com.example.delivery.service;

import com.example.delivery.dto.User;
import com.example.delivery.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;

    public User findById(String userId) {
        return userMapper.selectByUserId(userId);
    }

    public int add(User user) {
        if (user == null) {
            return 0;
        }
        return userMapper.insert(user);
    }
}
