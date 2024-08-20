package com.munhak.api.enums;

public enum TableNmType {

    tbl_original_text_master("원문마스터"),
    tbl_participant("참여자"),
    tbl_video_conference("화상회의"),
    tbl_original_text_reference("원문참조"),
    tbl_original_text_receive("원문수신"),
    tbl_original_text_dispatch("원문발신"),
    tbl_work_execute_info("업무수행정보"),
    tbl_work_execute_his("업무수행이력"),
    tbl_original_text_attach_file("원문첨부파일"),
    tbl_work_connect_his("업무접속이력"),
    tbl_login_his("로그인이력"),
    tbl_batch_job_his("배치작업이력")
    ;

    final private String name;

    public String getName() {
        return name;
    }

    private TableNmType(String name){
        this.name = name;
    }
}
