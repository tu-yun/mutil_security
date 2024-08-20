package com.munhak.api.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForm {

    private Integer pageSize;
    private Long offset;
    private Integer page;

    private String searchRole;      //권한
    private String searchWord;      //검색어
    private String searchType;      //구분

    private String searchYear;      //년
    private String searchMonth;     //월
    private String searchDay;       //일
    private String searchDt;        //특정일
    private String searchStDt;      //시작일
    private String searchEdDt;      //종료일

    private String orderType;       //정렬

}
