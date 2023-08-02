package com.mz.ihangbot.member.repository;

import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select distinct m from Member m " +
            "where m.username =:username")
    MemberResponseDTO findMemberById(@Param("username")String username);

    @Query("select distinct m from Member m " +
            "where m.username =:username")
    Optional<Member> findByUserName(@Param("username")String username);
}
