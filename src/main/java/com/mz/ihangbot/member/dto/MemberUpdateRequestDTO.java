package com.mz.ihangbot.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDTO {


    @NotNull
    public String child_name;

    @NotNull
    public int child_age;

    @NotNull
    public boolean child_gender;

    public String password;
    public String password_check;
}
