package com.flab.CafeMap.domain.user.dao;

import com.flab.CafeMap.domain.user.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    int updateUser(User user);
    Optional<User> findById(Long id);
}
