package com.mz.ihangbot.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDTO {

    public String child_name;
    public int child_age;
    public boolean child_gender;
    public String email;
}
