package com.mz.ihangbot.report.dto;

import com.mz.ihangbot.keyWord.domain.KeyWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class SuggestionRequestDTO {
    public String keyword;

    public static SuggestionRequestDTO from (KeyWord keyword) {
        return SuggestionRequestDTO.builder()
                .keyword(keyword.getKeyword())
                .build();
    }
}