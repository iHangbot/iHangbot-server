package com.mz.ihangbot.keyWord.dto;

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

    public static ReportDTO from (List<KeyWordReportResponseDTO> keywords, List<String> concerns) {
        return ReportDTO.builder()
                .keywords(keywords)
                .concerns(concerns)
                .build();
    }
}
