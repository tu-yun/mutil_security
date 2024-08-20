package com.munhak.api.config.auth;

import com.munhak.api.dto.store.StoreDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class LoginStore implements UserDetails {

    private final StoreDto store;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + store.getStoreRole());
        authorities.add(() -> "ROLE_MEMBER");
        return authorities;
    }

    @Override
    public String getPassword() {
        return store.getPassword();
    }

    @Override
    public String getUsername() {
        return store.getStoreId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
