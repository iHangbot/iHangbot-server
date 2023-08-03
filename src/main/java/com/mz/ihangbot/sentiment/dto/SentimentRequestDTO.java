package com.mz.ihangbot.sentiment.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SentimentRequestDTO {
    public String content;
    public String negative;
    public String positive;
    public String neutral;
    public String date;
    public String username;

}
