package com.mz.ihangbot.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class ReportDTO {

    List<KeyWordReportResponseDTO> keywords;
    List<String> concerns;
    SentimentReportResponseDTO sentiments;
    String suggestion;


    public static ReportDTO from (List<KeyWordReportResponseDTO> keywords, List<String> concerns, SentimentReportResponseDTO sentiments, String suggestion) {
        return ReportDTO.builder()
                .keywords(keywords)
                .concerns(concerns)
                .sentiments(sentiments)
                .suggestion(suggestion)
                .build();
    }
}
