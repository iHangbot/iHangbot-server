package com.mz.ihangbot.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

    @NotNull
    public String username;

    @NotNull
    public String password;

    @NotNull
    public String check_password;

    @NotNull
    public String child_name;

    @NotNull
    public int child_age;

    @NotNull
    public boolean child_gender;
}
