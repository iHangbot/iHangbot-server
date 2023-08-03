package com.mz.ihangbot.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeyWordReportResponseDTO {

    private final String keyword;
    private final Long count;
}
