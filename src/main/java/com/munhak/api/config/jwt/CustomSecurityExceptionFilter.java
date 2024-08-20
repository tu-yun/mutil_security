package com.munhak.api.config.jwt;

import com.munhak.api.common.utils.CustomResponseUtil;
import com.munhak.api.dto.common.ResponseDto;
import com.munhak.api.enums.ResultCodeType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomSecurityExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (Exception e){
            if(!(e instanceof AuthenticationException)) {
                CustomResponseUtil.fail(response, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
