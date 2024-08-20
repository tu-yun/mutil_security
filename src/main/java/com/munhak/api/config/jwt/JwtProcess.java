package com.munhak.api.config.jwt;

import com.munhak.api.config.auth.LoginMember;
import com.munhak.api.config.auth.LoginStore;
import com.munhak.api.dto.member.MemberDto;
import com.munhak.api.dto.store.StoreDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtProcess {
    private final Logger log = LoggerFactory.getLogger(getClass());

    //멤버 토큰 생성
    public static String createMemberToken(LoginMember loginMember) throws UnsupportedEncodingException {
        String jwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("chl", "member")
                .setExpiration(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
                .claim("seq", loginMember.getMember().getMemberSeq())
                .claim("id", loginMember.getMember().getMemberId())
                .claim("name", loginMember.getMember().getMemberNm())
                .claim("role", loginMember.getMember().getMemberRole())
                .signWith(SignatureAlgorithm.HS512, JwtVO.SECRET)
                .compact();
        return JwtVO.TOKEN_PREFIX + jwtToken;
    }

    // 서점 토큰 생성
    public static String createStoreToken(LoginStore loginStore) {
        String jwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("chl", "store")
                .setExpiration(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
                .claim("seq", loginStore.getStore().getStoreSeq())
                .claim("id", loginStore.getStore().getStoreId())
                .claim("name", loginStore.getStore().getStoreNm())
                .claim("role", loginStore.getStore().getStoreRole())
                .signWith(SignatureAlgorithm.HS512, JwtVO.SECRET)
                .compact();
        return JwtVO.TOKEN_PREFIX + jwtToken;
    }

    //토큰 검증 (return 되는 LoginStore 객체를 강제로 시큐리티 세션에 직접 주입할 예정)
    public static UserDetails verify(String token) {
        JwsHeader header = Jwts.parser().setSigningKey(JwtVO.SECRET).parseClaimsJws(token).getHeader();
        String channel = (String) header.get("chl");

        if(channel != null && channel.equals("member")){
            MemberDto sessionDto = new MemberDto();
            Claims claims = Jwts.parser().setSigningKey(JwtVO.SECRET).parseClaimsJws(token).getBody();
            sessionDto.setMemberSeq(Long.valueOf(claims.get("seq").toString()));
            sessionDto.setMemberId((String) claims.get("id"));
            sessionDto.setPassword("[PROTECTED]");
            sessionDto.setMemberNm((String) claims.get("name"));
            sessionDto.setMemberRole((String) claims.get("role"));

            return new LoginMember(sessionDto);
        } else if(channel != null && channel.equals("store")){
            StoreDto sessionDto = new StoreDto();
            Claims claims = Jwts.parser().setSigningKey(JwtVO.SECRET).parseClaimsJws(token).getBody();
            sessionDto.setStoreSeq(Long.valueOf(claims.get("seq").toString()));
            sessionDto.setStoreId((String) claims.get("id"));
            sessionDto.setPassword("[PROTECTED]");
            sessionDto.setStoreNm((String) claims.get("name"));
            sessionDto.setStoreRole((String) claims.get("role"));

            return new LoginStore(sessionDto);
        }
        return null;
    }
}