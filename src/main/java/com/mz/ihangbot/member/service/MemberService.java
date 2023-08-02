package com.mz.ihangbot.member.service;

import com.mz.ihangbot.common.exception.BusinessException;
import com.mz.ihangbot.common.exception.EntityNotFoundException;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.common.exception.InvalidValueException;
import com.mz.ihangbot.member.dto.MemberRequestDTO;
import com.mz.ihangbot.member.repository.MemberRepository;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDTO addMember(MemberRequestDTO requestDTO) {
        String child_name = requestDTO.child_name;
        String username = requestDTO.username;;
        String password = requestDTO.password;;
        String check_password = requestDTO.check_password;;
        String email = requestDTO.email;
        int child_age = requestDTO.child_age;
        boolean child_gender = requestDTO.child_gender;

        validateUnique(username);

        if (!password.equals(check_password))
            throw new InvalidValueException(ErrorCode.WRONG_PASSWORD);

        Member member = Member.builder()
                .child_name(child_name)
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .child_age(child_age)
                .child_gender(child_gender)
                .build();

        Member saved = memberRepository.save(member);
        return MemberResponseDTO.from(saved);
    }

    public Member findMember(String username) {
        return memberRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public MemberResponseDTO login(String username, String password) {
        Member member = findMember(username);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new InvalidValueException(ErrorCode.INVALID_PASSWORD);
        }

        return MemberResponseDTO.from(member);
    }

    @Transactional
    public MemberResponseDTO getMember(String memberId) {
        return memberRepository.findMemberById(memberId);
    }

    @Transactional
    public void updateMember(String username, String child_name, int child_age, boolean child_gender, String email) {
        Member member = findMember(username);
        member.update(child_name, child_age, child_gender, email);
        memberRepository.save(member);
    }

    private void validateUnique(String username) {
        if (memberRepository.findByUserName(username).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATED_USERNAME);
        }
    }
}
