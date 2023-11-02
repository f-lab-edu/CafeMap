package com.flab.CafeMap.web.cafe;

import com.flab.CafeMap.domain.cafe.service.CafeService;
import com.flab.CafeMap.web.cafe.dto.CafeSaveRequest;
import com.flab.CafeMap.web.cafe.dto.CafeSaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController : @Controller + @ResponseBody
 * @RequiredArgsConstructor : final 필드에 대해 생성자 생성
 * @RequestMapping : 요청에 맞는 컨트롤러 매핑
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private final CafeService cafeService;

    @PostMapping
    public ResponseEntity<CafeSaveResponse> addCafe(@RequestBody CafeSaveRequest cafeSaveRequest) {
        cafeService.addCafe(cafeSaveRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
