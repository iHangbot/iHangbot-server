package com.mz.ihangbot.sentiment.service;

import com.mz.ihangbot.common.exception.EntityNotFoundException;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.repository.MemberRepository;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import com.mz.ihangbot.sentiment.dto.SentimentResponseDTO;
import com.mz.ihangbot.sentiment.repository.SentimentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class SentimentService {

    private final SentimentRepository sentimentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SentimentResponseDTO addSentiment(String content, double negative, double positive, double neutral, String username, Date date) {
        Member member = memberRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Sentiment sentiment = Sentiment.builder()
                .content(content)
                .negative(negative)
                .positive(positive)
                .neutral(neutral)
                .member(member)
                .date(date)
                .build();

        Sentiment saved = sentimentRepository.save(sentiment);
        return SentimentResponseDTO.from(saved);
    }
}
