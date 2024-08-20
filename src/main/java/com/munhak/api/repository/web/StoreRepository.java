package com.munhak.api.repository.web;

import com.munhak.api.domain.web.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreId(String username);

}
