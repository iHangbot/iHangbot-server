package com.mz.ihangbot.keyWord.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConcernRequestDTO {
    public String category;
    public String confidence;
    public String username;
    public String date;
}
