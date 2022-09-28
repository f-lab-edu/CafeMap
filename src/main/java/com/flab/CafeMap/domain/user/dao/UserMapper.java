package com.flab.CafeMap.domain.user.dao;

import com.flab.CafeMap.domain.user.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    int updateUser(User user);

    Optional<User> selectUserById(Long id);
    Optional<User> selectUserByLoginId(String loginId);
}
