package com.mz.ihangbot.member.controller;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.common.exception.InvalidValueException;
import com.mz.ihangbot.member.dto.MemberRequestDTO;
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
    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    public ResponseEntity<BasicResponse> addMember(@RequestBody MemberRequestDTO requestDTO) {
        String child_name = requestDTO.child_name;
        String user_id = requestDTO.user_id;;
        String password = requestDTO.password;;
        String check_password = requestDTO.check_password;;
        String email = requestDTO.email;

        if (!password.equals(check_password))
            return basicResponse.ok(
                    new InvalidValueException(ErrorCode.WRONG_PASSWORD)
            );
        return basicResponse.ok(
                memberService.addMember(child_name, user_id, password, email)
        );
    }

    @GetMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    public ResponseEntity<BasicResponse> findMember(@RequestParam String user_id, @RequestParam String password) {
        return basicResponse.ok(
                memberService.login(user_id, password)
        );
    }
}
