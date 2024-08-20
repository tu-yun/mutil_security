package com.munhak.api.dto.login;

import com.munhak.api.common.annotation.EnumPattern;
import com.munhak.api.enums.RegExpType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @Schema(description = "아이디(이메일)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String username;
    @Schema(description = "비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @EnumPattern(regExpType = RegExpType.PWD)
    private String password;

}
