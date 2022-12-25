package com.example.delivery.mapper;

import com.example.delivery.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    int insert(User user);

    User selectByUserId(String userId);

    int update(User user);
}
