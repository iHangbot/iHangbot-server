package com.mz.ihangbot.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SentimentReportResponseDTO {
    private final double negThisWeek;
    private final double posThisWeek;
    private final double neuThisWeek;
    private final double negLastWeek;
    private final double posLastWeek;
    private final List<String> negData;
    private final List<String> posData;

    public static SentimentReportResponseDTO from (double negThisWeek, double posThisWeek, double neuThisWeek, double negLastWeek, double posLastWeek, List<String> negData, List<String> posData) {
        return SentimentReportResponseDTO.builder()
                .negThisWeek(negThisWeek)
                .posThisWeek(posThisWeek)
                .neuThisWeek(neuThisWeek)
                .negLastWeek(negLastWeek)
                .posLastWeek(posLastWeek)
                .negData(negData)
                .posData(posData)
                .build();
    }
}
