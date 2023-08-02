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
    private final Long id;
    private final String child_name;
    private final String username;
    private final String password;
    private final String email;
    private final int child_age;
    private final boolean child_gender;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd",
            locale = "Asia/Seoul"
    )
    private final LocalDateTime createdAt;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd",
            locale = "Asia/Seoul"
    )
    private final LocalDateTime modifiedAt;

    public static MemberResponseDTO from (Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .child_name(member.getChild_name())
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .child_age(member.getChild_age())
                .child_gender(member.isChild_gender())
                .build();
    }
}
