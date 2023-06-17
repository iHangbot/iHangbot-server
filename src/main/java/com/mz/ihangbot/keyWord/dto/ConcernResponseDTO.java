package com.mz.ihangbot.keyWord.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mz.ihangbot.keyWord.domain.Concern;
import com.mz.ihangbot.keyWord.domain.KeyWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class ConcernResponseDTO {
    private final Long id;
    private final String category;
    private final double confidence;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "MM/dd",
            locale = "Asia/Seoul"
    )
    private final Date date;

    public static ConcernResponseDTO from (Concern concern) {
        return ConcernResponseDTO.builder()
                .id(concern.getId())
                .category(concern.getCategory())
                .confidence(concern.getConfidence())
                .date(concern.getDate())
                .build();
    }
}
