package com.mz.ihangbot.report.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mz.ihangbot.keyWord.repository.ConcernRepository;
import com.mz.ihangbot.keyWord.repository.KeyWordRepository;
import com.mz.ihangbot.report.dto.*;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import com.mz.ihangbot.sentiment.repository.SentimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final KeyWordRepository keyWordRepository;
    private final ConcernRepository concernRepository;
    private final SentimentRepository sentimentRepository;

    @Transactional
    public ReportDTO getReportData(String username) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);

        List<KeyWordReportResponseDTO> keyWords = keyWordRepository.getKeyWords(username, cal.getTime());
        List<String> concerns = concernRepository.getConcerns(username, cal.getTime());
        SentimentReportResponseDTO sentiments = getSentimentData(username, cal.getTime());
        String suggestion = getSuggestionData(username, cal.getTime());

        return ReportDTO.from(keyWords, concerns, sentiments, suggestion);
    }

    @Transactional
    public SentimentReportResponseDTO getSentimentData(String username, Date aWeekAgo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -14);

        Date now = new Date();

        List<Sentiment> sentimentLast = sentimentRepository.findByUsernameAndDate(username, aWeekAgo, cal.getTime());
        List<Sentiment> sentimentThis = sentimentRepository.findByUsernameAndDate(username, now, aWeekAgo);

        double negLastWeek, posLastWeek;
        if (sentimentLast.isEmpty()) {
            negLastWeek = 0;
            posLastWeek = 0;
        }
        else {
            negLastWeek = sentimentRepository.getNegative(username, aWeekAgo, cal.getTime());
            posLastWeek = sentimentRepository.getPositive(username, aWeekAgo, cal.getTime());
        }

        double negThisWeek, posThisWeek, neuThisWeek;
        if (sentimentThis.isEmpty()) {
            negThisWeek = 0;
            posThisWeek = 0;
            neuThisWeek = 0;
        }
        else {
            negThisWeek = sentimentRepository.getNegative(username, now, aWeekAgo);
            posThisWeek = sentimentRepository.getPositive(username, now, aWeekAgo);
            neuThisWeek = sentimentRepository.getNeutral(username, now, aWeekAgo);
        }
        List<String> negData = sentimentRepository.getNegData(username, aWeekAgo);
        List<String> posData = sentimentRepository.getPosData(username, aWeekAgo);

        return new SentimentReportResponseDTO(negThisWeek, posThisWeek, neuThisWeek, negLastWeek, posLastWeek, negData, posData);
    }

    @Transactional
    public String getSuggestionData(String username, Date aWeekAgo) {
        List<SuggestionRequestDTO> requestDTO = getKeyWord(username, aWeekAgo);
        String suggestion = getGptData(requestDTO);

        return suggestion;
    }

    @Transactional
    public List<SuggestionRequestDTO> getKeyWord(String username, Date aWeekAgo) {
        return keyWordRepository.findAllByUsernameAndDate(username, aWeekAgo).stream()
                .map(SuggestionRequestDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public String getGptData(List<SuggestionRequestDTO> requestDTO) {
        String pythonServerUrl = "http://13.124.53.159:8079";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(pythonServerUrl + "/postSuggestion", requestDTO, JsonNode.class);

        JsonNode jsonResponse = response.getBody();
        String content = jsonResponse.get("message").asText();

        return content;
    }
}
