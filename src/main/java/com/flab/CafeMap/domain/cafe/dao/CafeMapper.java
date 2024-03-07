package com.flab.CafeMap.domain.cafe.dao;

import com.flab.CafeMap.domain.cafe.Cafe;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Mapper : MyBatis 매핑 xml의 SQL을 호출하기 위한 인터페이스
 */

@Mapper
public interface CafeMapper {

    int insertCafe(Cafe cafe);

    Optional<Cafe> selectCafeById(long id);

    Optional<Cafe> selectCafeByLoginId(String loginId);
}