package com.mz.ihangbot.member.service;

import com.mz.ihangbot.member.repository.MemberRepository;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDTO addMember(String child_name, String user_id, String password, String email) {
        Member member = Member.builder()
                .child_name(child_name)
                .user_id(user_id)
                .password(password)
                .email(email)
                .build();

        Member saved = memberRepository.save(member);
        return MemberResponseDTO.from(saved);
    }

    @Transactional
    public MemberResponseDTO login(String user_id, String password) {
        return memberRepository.findMemberByIdAndPassword(user_id, password);
    }
}
