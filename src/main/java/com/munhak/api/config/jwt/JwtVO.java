package com.munhak.api.config.jwt;

public interface JwtVO {
    public static final String SECRET = "test"; // HS256 (대칭키)
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 1; // 하루
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
