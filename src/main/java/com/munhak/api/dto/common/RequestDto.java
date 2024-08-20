package com.munhak.api.dto.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestDto<T> {
    /**
     * 결과 코드
     * 00 : 성공, 01 : 실패 (ResultCodeType)
     */
    private final String key;

    /**
     * parameter
     * json or object
     */
//    private final T data;

    /**
     * parameters
     * json or object
     */
    private final T dataList;
}
