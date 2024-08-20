package com.munhak.api.config.auth;

import com.munhak.api.domain.web.Member;
import com.munhak.api.dto.member.MemberDto;
import com.munhak.api.repository.web.MemberRepository;
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
public class LoginMemberService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("디버그 : loadUserByUsername(LoginMemberService) 호출됨");

        Member member = memberRepository.findByMemberId(username);
        if (member == null || member.getMemberSeq() == null) {
            throw new InternalAuthenticationServiceException("인증 실패");
        }
        return new LoginMember(new MemberDto(member));
    }

}
