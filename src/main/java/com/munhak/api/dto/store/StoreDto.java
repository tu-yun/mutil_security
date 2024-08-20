package com.munhak.api.dto.store;

import com.munhak.api.domain.web.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreDto {

    private Long storeSeq;
    private String storeId;
    private String password;
    private String storeNm;
    private String storeRole;

    public StoreDto(Store store) {
        this.storeSeq = store.getStoreSeq();
        this.storeId = store.getStoreId();
        this.password = store.getPassword();
        this.storeNm = store.getStoreNm();
        this.storeRole = store.getStoreRole();
    }

}
