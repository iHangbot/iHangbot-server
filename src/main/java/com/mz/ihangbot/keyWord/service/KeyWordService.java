package com.mz.ihangbot.keyWord.service;

import com.mz.ihangbot.keyWord.domain.Concern;
import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.keyWord.dto.ConcernResponseDTO;
import com.mz.ihangbot.keyWord.dto.KeyWordReportResponseDTO;
import com.mz.ihangbot.keyWord.dto.KeyWordResponseDTO;
import com.mz.ihangbot.keyWord.dto.ReportDTO;
import com.mz.ihangbot.keyWord.repository.ConcernRepository;
import com.mz.ihangbot.keyWord.repository.KeyWordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeyWordService {

    private final KeyWordRepository keyWordRepository;
    private final ConcernRepository concernRepository;

    @Transactional
    public KeyWordResponseDTO addKeyWord(String keyword, int count, Date date) {
        KeyWord keyWord = KeyWord.builder()
                .keyword(keyword)
                .count(count)
                .date(date)
                .build();

        KeyWord saved = keyWordRepository.save(keyWord);
        return KeyWordResponseDTO.from(saved);
    }

    @Transactional
    public ConcernResponseDTO addConcern(String category, double confidence, Date date) {
        Concern concern = Concern.builder()
                .category(category)
                .confidence(confidence)
                .date(date)
                .build();

        Concern saved = concernRepository.save(concern);
        return ConcernResponseDTO.from(saved);
    }

    public ReportDTO getReportData() {
        List<KeyWordReportResponseDTO> keyWords = keyWordRepository.getKeyWords();
        List<String> concerns = concernRepository.getConcerns();

        return ReportDTO.from(keyWords, concerns);
    }

}
