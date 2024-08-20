#mutil_security

Question : Springboot 1개 서버에 여러 사이트를 함께 구현하게 되면서 멤버와 서점를 별도 인증이 필요하게 됨.

Answer : SecurityFilterChain을 멤버 인증용과 서점 인증용 2개의 별도 로그인 절차를 따르도록 구현함.

[소스 소개]
1. src/main/java/com/munhak/api/config/SecurityConfig.java
  - SecurityFilterChain을 FilterChain(멤버용)과 localBookstoreFilterChain(서점용)을 2개를 생성하여 AuthenticationManager등록함.

2. src/main/java/com/munhak/api/config/jwt/JwtAuthenticationFilter.java
  - UsernamePasswordAuthenticationFilter를 상속받은 멤버용 인증 Filter이며 인증시 멤버용 JWT 토큰을 생성한다.

3. src/main/java/com/munhak/api/config/jwt/JwtAuthenticationStoreFilter.java
   - UsernamePasswordAuthenticationFilter를 상속받은 서점용 인증 Filter이며 인증시 서점용 JWT 토큰을 생성한다.
  
4. src/main/java/com/munhak/api/config/auth/LoginMemberService.java
   - 멤버용 UserDetailsService이며 member 테이블에서 회원을 조회한다.

5. src/main/java/com/munhak/api/config/auth/LoginStoreService.java
   - 서점용 UserDetailsService이며 store 테이블에서 서점을 조회한다.
  
6. src/main/java/com/munhak/api/config/auth/LoginMember.java
   - 멤버용 UserDetails이며 인증된 회원의 정보를 가진다.
  
7. src/main/java/com/munhak/api/config/auth/LoginStore.java
   - 멤버용 UserDetails이며 인증된 서점의 정보를 가진다.
  
8. src/main/java/com/munhak/api/config/jwt/JwtAuthorizationFilter.java
   - BasicAuthenticationFilter를 상속받은 인가용 Filter이며 JWT토큰의 성격에 따라 멤버 또는 서점의 인가를 진행한다.
