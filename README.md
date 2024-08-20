#다중 Spring Security

- 1개의 서버에 여러 사이트를 구현하게 되면서 다중 로그인을 구현하게 됨.

- SecurityConfig.java 설정에서 FilterChain 및 인증filter를 여러개로 등록하고 요청된 url에 따라 해당하는 FilterChain 인증 절차를 따르도록 구현함.

- FilterChain에 따라 UserDetailsService를 별도로 설정하여 인증을 테이블별로 진행함.
