package com.mz.ihangbot.keyWord.dto;

import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.sentiment.dto.SentimentReportResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
public class KeyWordReportResponseDTO {

    private final String keyword;
    private final Long count;
}
