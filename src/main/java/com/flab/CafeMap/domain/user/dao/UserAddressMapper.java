package com.flab.CafeMap.domain.user.dao;

import com.flab.CafeMap.domain.user.User;
import com.flab.CafeMap.domain.user.UserAddress;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Mapper : MyBatis 매핑 xml의 SQL을 호출하기 위한 인터페이스
 */

@Mapper
public interface UserAddressMapper {

    int insertUserAddress(UserAddress userAddress);

    Optional<UserAddress> selectUserAddressByLoginId(String loginId);

    Optional<User> selectUserAddressByUserId(Long userId);
}
