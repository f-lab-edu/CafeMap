package com.flab.CafeMap.domain.user.dao;

import com.flab.CafeMap.domain.user.UserAddress;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper {

    int insertUserAddress(UserAddress userAddress);

    Optional<UserAddress> selectUserAddressByLoginId(String loginId);
}
