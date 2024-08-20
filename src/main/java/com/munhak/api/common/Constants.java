package com.munhak.api.common;

public class Constants {

    /*페이징*/
    public static final int DEFAULT_PAGESIZE = 10;
    public static final int MOBILE_PAGESIZE = 5;
    public static final int DEFAULT_THUMBNAIL_PAGESIZE = 6;
    public static final int DEFAULT_MAIN_NOTICE_PAGESIZE = 6;
    public static final int DEFAULT_MAIN_NEWS_PAGESIZE = 4;
    public static final int[] PAGE_SIZES = {10, 20, 30, 50, 100, 0};    // 0 : 전체

    /*첨부파일*/
    public static final Long imageMaxFileSize = 1024 * 500L;       //이미지 업로드 최대 사이즈 (Byte)
    public static final Long bigImageMaxFileSize = 1024 * 1024 * 10L;    //큰 이미지 업로드 최대 사이즈 (Byte)
    public static final Long docMaxFileSize = 1024 * 1024 * 10L;    //문서 업로드 최대 사이즈 (Byte)
    public static final String allowExtFileImg = "jpg,jpeg,gif,png,bmp";
    public static final String allowExtFileDoc = "doc,xls,docx,xlsx,ppt,pptx,hwp,pdf";
    public static final String allowExtFileBusi = "jpg,jpeg,pdf";
    public static final String allowExtFile = "jpg,jpeg,gif,png,bmp,doc,xls,docx,xlsx,ppt,pptx,hwp,pdf";

    public static final String API_DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
