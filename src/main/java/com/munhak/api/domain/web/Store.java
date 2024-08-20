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

@Table(name = "T_STORE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "STORE_SEQ")
    private Long storeSeq;
    @Column(name= "STORE_ID")
    private String storeId;
    @Column(name= "PASSWORD")
    private String password;
    @Column(name= "STORE_NM")
    private String storeNm;
    @Column(name= "STORE_ROLE")
    private String storeRole;
    
}
