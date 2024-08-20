package com.munhak.api.dto.common;

import com.munhak.api.enums.ResultCodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {
    /**
     * 결과 코드
     * 00 : 성공, 01 : 실패
     */
    @Schema(description = "결과 코드 (00:성공, 01:실패)")
    private final String code;

    /**
     * 결과 메세지
     */
    @Schema(description = "결과 메세지")
    private final String message;


    /**
     * return 데이터
     * json or object
     */
    @Schema(description = "성공시 데이터")
    private final T data;

    /**
     * return 에러 데이터
     * json or object
     */
    @Schema(description = "실패시 데이터")
    private final Object errorList;

    public ResponseDto(ResultCodeType resultCodeType, String message, T data) {
        this(resultCodeType, message, data, null);
    }

    public ResponseDto(ResultCodeType resultCodeType, String message, T data, Object errorList) {
        this.code = resultCodeType.code();
        this.message = message;
        this.data = data;
        this.errorList = errorList;
    }
}
