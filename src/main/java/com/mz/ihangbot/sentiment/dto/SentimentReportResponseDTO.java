package com.mz.ihangbot.sentiment.dto;

import com.mz.ihangbot.sentiment.domain.Sentiment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SentimentReportResponseDTO {
    private final double negative;
    private final double positive;
    private final double neutral;
    private final List<String> negData;
    private final List<String> posData;

    public static SentimentReportResponseDTO from (double negative, double positive, double neutral, List<String> negData, List<String> posData) {
        return SentimentReportResponseDTO.builder()
                .negative(negative)
                .positive(positive)
                .neutral(neutral)
                .negData(negData)
                .posData(posData)
                .build();
    }
}
