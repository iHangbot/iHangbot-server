package com.mz.ihangbot.keyWord.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeyWordRequestDTO {
    public String keyword;
    public String username;
    public String count;
    public String date;
}
