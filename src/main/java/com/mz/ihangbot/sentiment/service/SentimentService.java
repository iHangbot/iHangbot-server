package com.mz.ihangbot.sentiment.service;

import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.dto.MemberResponseDTO;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import com.mz.ihangbot.sentiment.dto.SentimentReportResponseDTO;
import com.mz.ihangbot.sentiment.dto.SentimentResponseDTO;
import com.mz.ihangbot.sentiment.repository.SentimentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SentimentService {

    private final SentimentRepository sentimentRepository;

    @Transactional
    public SentimentResponseDTO addSentiment(String content, double negative, double positive, double neutral, Date date) {
        Sentiment sentiment = Sentiment.builder()
                .content(content)
                .negative(negative)
                .positive(positive)
                .neutral(neutral)
                .date(date)
                .build();

        Sentiment saved = sentimentRepository.save(sentiment);
        return SentimentResponseDTO.from(saved);
    }

    @Transactional
    public SentimentReportResponseDTO getReportData() {
        double negative = sentimentRepository.getNegative();
        double positive = sentimentRepository.getPositive();
        double neutral = sentimentRepository.getNeutral();
        List<String> negData = sentimentRepository.getNegData();
        List<String> posData = sentimentRepository.getPosData();

        return new SentimentReportResponseDTO(negative, positive, neutral, negData, posData);
    }
}
