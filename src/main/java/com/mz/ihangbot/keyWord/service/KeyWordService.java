package com.mz.ihangbot.keyWord.service;

import com.mz.ihangbot.common.exception.EntityNotFoundException;
import com.mz.ihangbot.common.exception.ErrorCode;
import com.mz.ihangbot.keyWord.domain.Concern;
import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.keyWord.dto.*;
import com.mz.ihangbot.keyWord.repository.ConcernRepository;
import com.mz.ihangbot.keyWord.repository.KeyWordRepository;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeyWordService {

    private final KeyWordRepository keyWordRepository;
    private final ConcernRepository concernRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public KeyWordResponseDTO addKeyWord(String keyword, String username, int count, Date date) {
        Member member = memberRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        KeyWord keyWord = KeyWord.builder()
                .keyword(keyword)
                .member(member)
                .count(count)
                .date(date)
                .build();

        KeyWord saved = keyWordRepository.save(keyWord);
        return KeyWordResponseDTO.from(saved);
    }

    @Transactional
    public ConcernResponseDTO addConcern(String category, double confidence, String username, Date date) {
        Member member = memberRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Concern concern = Concern.builder()
                .category(category)
                .confidence(confidence)
                .member(member)
                .date(date)
                .build();

        Concern saved = concernRepository.save(concern);
        return ConcernResponseDTO.from(saved);
    }

}
