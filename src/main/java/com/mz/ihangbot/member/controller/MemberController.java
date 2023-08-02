package com.mz.ihangbot.member.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.common.exception.InvalidValueException;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberRequestDTO;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import com.mz.ihangbot.member.dto.MemberUpdateRequestDTO;
import com.mz.ihangbot.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "member", description = "member API")
public class MemberController {

    private final BasicResponse basicResponse = new BasicResponse();
    private final MemberService memberService;


    @PostMapping("/signUp")
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다. 남자는 true(1), 여자는 false(0)입니다.")
    public ResponseEntity<BasicResponse> addMember(@RequestBody MemberRequestDTO requestDTO) {
        return basicResponse.ok(
                memberService.addMember(requestDTO)
        );
    }

    @GetMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    public ResponseEntity<BasicResponse> findMember(@RequestParam String username, @RequestParam String password) {
        return basicResponse.ok(
                memberService.login(username, password)
        );
    }

    @GetMapping("/{username}/profile")
    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회합니다.")
    public ResponseEntity<BasicResponse> getMember(@PathVariable ("username") String username) {
        return basicResponse.ok(
                memberService.getMember(username)
        );
    }

    @PutMapping("/{username}/profile/setting")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ResponseEntity<BasicResponse> updateMember(@PathVariable("username") String username, @RequestBody MemberUpdateRequestDTO requestDTO) {
        MemberResponseDTO memberDTO = memberService.getMember(username);

        String child_name = requestDTO.child_name;
        int child_age = requestDTO.child_age;
        boolean child_gender = requestDTO.child_gender;
        String email = requestDTO.email;

        if (memberDTO.getChild_name().equals(child_name) || child_name.equals("string"))
            child_name = memberDTO.getChild_name();
        if (memberDTO.getChild_age() == child_age || child_age == 0)
            child_age = memberDTO.getChild_age();
        if (memberDTO.isChild_gender() == child_gender)
            child_gender = memberDTO.isChild_gender();
        if (memberDTO.getEmail().equals(email) || email.equals("string"))
            email = memberDTO.getEmail();

        memberService.updateMember(username, child_name, child_age, child_gender, email);
        return basicResponse.noContent();
    }

}
