package com.mz.ihangbot.sentiment.repository;

import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {

    @Query("select avg(s.negative) from Sentiment s")
    double getNegative();

    @Query("select avg(s.positive) from Sentiment s")
    double getPositive();

    @Query("select avg(s.neutral) from Sentiment s")
    double getNeutral();

    @Query("select s.content from Sentiment s order by s.negative DESC limit 5")
    List<String> getNegData();

    @Query("select s.content from Sentiment s order by s.positive DESC limit 5")
    List<String> getPosData();
}
