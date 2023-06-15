package com.mz.ihangbot.member.service;

import com.mz.ihangbot.common.exception.EntityNotFoundException;
import com.mz.ihangbot.common.exception.ErrorCode;
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
    public MemberResponseDTO addMember(String child_name, String user_id, String password, String email, int child_age, boolean child_gender) {
        Member member = Member.builder()
                .child_name(child_name)
                .user_id(user_id)
                .password(password)
                .email(email)
                .child_age(child_age)
                .child_gender(child_gender)
                .build();

        Member saved = memberRepository.save(member);
        return MemberResponseDTO.from(saved);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public MemberResponseDTO login(String user_id, String password) {
        return memberRepository.findMemberByIdAndPassword(user_id, password);
    }

    @Transactional
    public MemberResponseDTO getMember(Long memberId) {
        Member member = findMember(memberId);
        return MemberResponseDTO.from(member);
    }

    @Transactional
    public void updateMember(Long memberId, String child_name, int child_age, boolean child_gender, String email) {
        Member member = findMember(memberId);
        member.update(child_name, child_age, child_gender, email);
        memberRepository.save(member);
    }
}
