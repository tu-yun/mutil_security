package com.munhak.api.repository.web;

import com.munhak.api.domain.web.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String username);

}
