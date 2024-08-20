package com.munhak.api.config.auth;

import com.munhak.api.common.utils.Base;
import com.munhak.api.dto.common.ResponseDto;
import com.munhak.api.dto.login.LoginForm;
import com.munhak.api.dto.member.MemberDto;
import com.munhak.api.dto.store.StoreDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Tag(name = "로그인관리")
public class LoginRestController extends Base {

    /**
     * 회원 로그인 API(swagger 노출용) - 실제 구동은 JwtAuthenticationStoreFilter에서 실행됨
     */
    @Operation(summary = "회원 로그인 API")
    @PostMapping("/member/login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    headers = @Header(name = "authorization", description = "Bearer Token(member)")
            )
    })
    public ResponseEntity<ResponseDto<MemberDto>> loginMember(@Valid @RequestBody LoginForm loginForm) {
        return null;
    }

    /**
     * 서점 로그인 API(swagger 노출용) - 실제 구동은 JwtAuthenticationStoreFilter에서 실행됨
     */
    @Operation(summary = "동네서점 로그인 API")
    @PostMapping("/store/login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    headers = @Header(name = "authorization", description = "Bearer Token(Store)")
            )
    })
    public ResponseEntity<ResponseDto<StoreDto>> loginStore(@Valid @RequestBody LoginForm loginForm) {
        return null;
    }

}
