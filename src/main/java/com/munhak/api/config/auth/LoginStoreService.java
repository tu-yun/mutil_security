package com.munhak.api.config.auth;

import com.munhak.api.domain.web.Store;
import com.munhak.api.dto.store.StoreDto;
import com.munhak.api.repository.web.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginStoreService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final StoreRepository storeRepository;

    // 시큐리티로 로그인이 될때, 시큐리티가 loadUserByUsername() 실행해서 username을 체크!!
    // 없으면 오류
    // 있으면 정상적으로 시큐리티 컨텍스트 내부 세션에 로그인된 세션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("디버그 : loadUserByUsername(LoginStoreService) 호출됨");

        Store store = storeRepository.findByStoreId(username);
        if (store == null || store.getStoreId() == null) {
            throw new InternalAuthenticationServiceException("인증 실패");
        }
        return new LoginStore(new StoreDto(store));
    }

}
