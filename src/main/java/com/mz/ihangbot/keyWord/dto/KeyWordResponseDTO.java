package com.mz.ihangbot.keyWord.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import com.mz.ihangbot.sentiment.dto.SentimentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class KeyWordResponseDTO {
    private final Long id;
    private final String keyword;
    private final int count;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "MM/dd",
            locale = "Asia/Seoul"
    )
    private final Date date;

    public static KeyWordResponseDTO from (KeyWord keyWord) {
        return KeyWordResponseDTO.builder()
                .id(keyWord.getId())
                .keyword(keyWord.getKeyword())
                .count(keyWord.getCount())
                .date(keyWord.getDate())
                .build();
    }
}
