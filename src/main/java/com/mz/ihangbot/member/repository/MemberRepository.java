package com.mz.ihangbot.member.repository;

import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select distinct m from Member m " +
            "where m.user_id = :user_id and " +
            "m.password = :password")
    MemberResponseDTO findMemberByIdAndPassword(@Param("user_id")String user_id, @Param("password")String password);
}
