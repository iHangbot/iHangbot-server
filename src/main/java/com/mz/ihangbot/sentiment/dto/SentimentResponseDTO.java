package com.mz.ihangbot.sentiment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SentimentResponseDTO {
    private final Long id;
    private final String content;
    private final double negative;
    private final double positive;
    private final double neutral;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "MM/dd",
            locale = "Asia/Seoul"
    )
    private final Date date;

    public static SentimentResponseDTO from (Sentiment sentiment) {
        return SentimentResponseDTO.builder()
                .id(sentiment.getId())
                .content(sentiment.getContent())
                .negative(sentiment.getNegative())
                .positive(sentiment.getPositive())
                .neutral(sentiment.getNeutral())
                .date(sentiment.getDate())
                .build();
    }
}
