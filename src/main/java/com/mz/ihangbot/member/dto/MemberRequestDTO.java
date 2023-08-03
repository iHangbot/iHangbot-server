package com.mz.ihangbot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

    public String username;
    public String password;
    public String check_password;
    public String child_name;
    public int child_age;
    public boolean child_gender;
}
