package com.mz.ihangbot.member.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.common.exception.InvalidValueException;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberRequestDTO;
import com.mz.ihangbot.member.dto.MemberUpdateRequestDTO;
import com.mz.ihangbot.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "member", description = "member API")
public class MemberController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final MemberService memberService;


    @PostMapping("/signUp")
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다. 남자는 true(1), 여자는 false(0)입니다.")
    public ResponseEntity<BasicResponse> addMember(@RequestBody MemberRequestDTO requestDTO) {
        String child_name = requestDTO.child_name;
        String user_id = requestDTO.user_id;;
        String password = requestDTO.password;;
        String check_password = requestDTO.check_password;;
        String email = requestDTO.email;
        int child_age = requestDTO.child_age;
        boolean child_gender = requestDTO.child_gender;

        if (!password.equals(check_password))
            return basicResponse.ok(
                    new InvalidValueException(ErrorCode.WRONG_PASSWORD)
            );
        return basicResponse.ok(
                memberService.addMember(child_name, user_id, password, email, child_age, child_gender)
        );
    }

    @GetMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    public ResponseEntity<BasicResponse> findMember(@RequestParam String user_id, @RequestParam String password) {
        return basicResponse.ok(
                memberService.login(user_id, password)
        );
    }

    @GetMapping("/{memberId}/profile")
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    public ResponseEntity<BasicResponse> getMember(@PathVariable ("memberId") Long memberId) {
        return basicResponse.ok(
                memberService.getMember(memberId)
        );
    }

    @PutMapping("/{memberId}/profile/setting")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ResponseEntity<BasicResponse> updateMember(@PathVariable("memberId") Long memberId, @RequestBody MemberUpdateRequestDTO requestDTO) {
        Member member = memberService.findMember(memberId);

        String child_name = requestDTO.child_name;
        int child_age = requestDTO.child_age;
        boolean child_gender = requestDTO.child_gender;
        String email = requestDTO.email;

        if (member.getChild_name().equals(child_name) || child_name.equals("string"))
            child_name = member.getChild_name();
        if (member.getChild_age() == child_age || child_age == 0)
            child_age = member.getChild_age();
        if (member.isChild_gender() == child_gender)
            child_gender = member.isChild_gender();
        if (member.getEmail().equals(email) || email.equals("string"))
            email = member.getEmail();

        memberService.updateMember(memberId, child_name, child_age, child_gender, email);
        return basicResponse.noContent();
    }

}
