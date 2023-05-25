package com.mz.ihangbot.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mz.ihangbot.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class MemberResponseDTO {
    private final String child_name;
    private final String user_id;
    private final String password;
    private final String email;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd",
            locale = "Asia/Seoul"
    )
    private final LocalDateTime createdAt;

    public static MemberResponseDTO from (Member member) {
        return MemberResponseDTO.builder()
                .child_name(member.getChild_name())
                .user_id(member.getUser_id())
                .password(member.getPassword())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
