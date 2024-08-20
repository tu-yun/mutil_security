package com.munhak.api.config;

import com.munhak.api.common.utils.CustomResponseUtil;
import com.munhak.api.config.auth.LoginMemberService;
import com.munhak.api.config.auth.LoginStoreService;
import com.munhak.api.config.jwt.CustomSecurityExceptionFilter;
import com.munhak.api.config.jwt.JwtAuthenticationFilter;
import com.munhak.api.config.jwt.JwtAuthenticationStoreFilter;
import com.munhak.api.config.jwt.JwtAuthorizationFilter;
import com.munhak.api.enums.Cltype;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final LoginMemberService loginMemberService;
    private final LoginStoreService LoginStoreService;

    //PasswordEncoder 설정
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        log.debug("디버그 : BCryptPasswordEncoder 빈 등록됨");
        return new BCryptPasswordEncoder();
    }

    //cors 설정
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트 앤드 IP만 허용 react)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        configuration.addExposedHeader("Authorization"); // 옛날에는 디폴트 였다. 지금은 아닙니다.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    //FilterChain 공통 설정
    private void commonFilterChain(HttpSecurity http) throws Exception {
    http.headers(headers -> headers.frameOptions(options -> options.disable()))
            .csrf(csrf -> csrf.disable())                                                                           //enable이면 postman 동작안함
            .cors(cors -> cors.configurationSource(configurationSource()))
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))     //jSessionId를 서버쪽에서 관리안함
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())                                                                    //httpBasic은 브라우저가 팝업창을 이용해서 사용자 인증을 진행한다.
            .exceptionHandling(handling -> handling.authenticationEntryPoint((request, response, e) -> {            //오류 핸들링
                CustomResponseUtil.fail(response, "로그인을 진행해 주세요", HttpStatus.UNAUTHORIZED);
            }))
            .exceptionHandling(handling -> handling.accessDeniedHandler((request, response, e) -> {
                CustomResponseUtil.fail(response, "권한이 없습니다", HttpStatus.FORBIDDEN);
            }))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/u/store/**")).hasRole("STORE")
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/u/member/**")).hasRole("MEMBER")
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/c/**")).authenticated()
                    .requestMatchers(new AntPathRequestMatcher("/api/v1/o/**")).permitAll()
                    .anyRequest().permitAll());
    }

    //서점 인증 FilterChain 설정
    @Bean
    @Order(0)
    SecurityFilterChain localBookstoreFilterChain(HttpSecurity http) throws Exception {
        log.debug("디버그 : localBookstore 로그인전용 FilterChain 빈 등록됨");

        commonFilterChain(http);

        http.securityMatcher("/store/login")
                .userDetailsService(LoginStoreService)
                .apply(new LocalBookstoreSecurityFilterManager());

        return http.build();
    }

    //서점 인증 Filter 등록
    private class LocalBookstoreSecurityFilterManager extends AbstractHttpConfigurer<LocalBookstoreSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager manager = http.getSharedObject(AuthenticationManager.class);
            http.addFilterAfter(new JwtAuthenticationStoreFilter(manager), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new CustomSecurityExceptionFilter(), LogoutFilter.class);
        }
    }

    //멤버 인증 FilterChain 설정
    @Bean
    @Order(1)
    SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
        log.debug("디버그 : 일반 FilterChain 빈 등록됨");

        commonFilterChain(http);

        http.userDetailsService(loginMemberService)
                .apply(new CustomSecurityFilterManager());

        return http.build();
    }

    //멤버 인증 Filter 등록
    private class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager manager = http.getSharedObject(AuthenticationManager.class);
            http.addFilterBefore(new JwtAuthorizationFilter(manager), UsernamePasswordAuthenticationFilter.class)
                    .addFilterAfter(new JwtAuthenticationFilter(manager), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new CustomSecurityExceptionFilter(), LogoutFilter.class);
        }
    }

    //security filter에서 swagger url 제외
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"), new AntPathRequestMatcher("/api-docs/**"));
    }

}