package com.munhak.api.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.munhak.api.common.utils.CustomResponseUtil;
import com.munhak.api.config.auth.LoginMember;
import com.munhak.api.dto.login.LoginForm;
import com.munhak.api.dto.member.MemberDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * 인증 필터
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    // Post : /api/login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        log.debug("디버그 : attemptAuthentication 호출됨");
        try {
            ObjectMapper om = new ObjectMapper();
            LoginForm memberLoginForm = om.readValue(request.getInputStream(), LoginForm.class);

            if (memberLoginForm.getUsername() == null) {
                throw new InternalAuthenticationServiceException("");
            }
            if (memberLoginForm.getPassword() == null) {
                throw new InternalAuthenticationServiceException("");
            }

            // 강제 로그인
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    memberLoginForm.getUsername(), memberLoginForm.getPassword());

            // UserDetailsService의 loadUserByUsername 호출
            // JWT를 쓴다 하더라도, 컨트롤러 진입을 하면 시큐리티의 권한체크, 인증체크의 도움을 받을 수 있게 세션을 만든다.
            // 이 세션의 유효기간은 request하고, response하면 끝!!
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (Exception e) {
            // unsuccessfulAuthentication 호출함
            throw new InternalAuthenticationServiceException("로그인 정보를 확인해 주시기 바랍니다.");
        }
    }

    // 로그인 실패
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        log.debug("디버그 : unsuccessfulAuthentication 호출됨");

        String message = failed.getMessage() != null ? failed.getMessage() : "로그인 실패";
        CustomResponseUtil.fail(response, message, HttpStatus.UNAUTHORIZED);
    }

    // 로그인 성공
    // return authentication 잘 작동하면 successfulAuthentication 메서드 호출됩니다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.debug("디버그 : successfulAuthentication 호출됨");

        LoginMember loginMember = (LoginMember) authResult.getPrincipal();
        String jwtToken = JwtProcess.createMemberToken(loginMember);
        response.addHeader(JwtVO.HEADER, jwtToken);

        CustomResponseUtil.success(response, "로그인 성공", loginMember.getMember());
    }

}
