package com.munhak.api.domain.web;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "T_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "MEMBER_SEQ")
    private Long memberSeq;
    @Column(name= "MEMBER_ID")
    private String memberId;
    @Column(name= "PASSWORD")
    private String password;
    @Column(name= "MEMBER_NM")
    private String memberNm;
    @Column(name= "MEMBER_ROLE")
    private String memberRole;

}
