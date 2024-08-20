package com.munhak.api.dto.member;

import com.munhak.api.domain.web.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long memberSeq;
    private String memberId;
    private String password;
    private String memberNm;
    private String memberRole;

    public MemberDto(Member member) {
        this.memberSeq = member.getMemberSeq();
        this.memberId = member.getMemberId();
        this.password = member.getPassword();
        this.memberNm = member.getMemberNm();
        this.memberRole = member.getMemberRole();
    }

}
