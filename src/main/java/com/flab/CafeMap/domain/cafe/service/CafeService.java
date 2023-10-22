package com.flab.CafeMap.domain.cafe.service;

import com.flab.CafeMap.domain.cafe.Cafe;
import com.flab.CafeMap.domain.cafe.dao.CafeMapper;
import com.flab.CafeMap.web.cafe.dto.CafeSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @Transactional : 선언적 트랜잭션에 사용되는 애노테이션으로 테스트 이후 롤백
 */

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeMapper cafeMapper;

    @Transactional
    public Cafe addCafe(CafeSaveRequest cafeSaveRequest) {
        Cafe cafe = cafeSaveRequest.toEntity();
        cafeMapper.insertCafe(cafe);
        return cafe;
    }
}
