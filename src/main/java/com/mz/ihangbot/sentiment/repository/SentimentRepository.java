package com.mz.ihangbot.sentiment.repository;

import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.sentiment.domain.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {

    @Query("select s from Sentiment s where :username = s.member.username and :aWeekAgo >= s.date and :twoWeekAgo <= s.date")
    List<Sentiment> findByUsernameAndDate(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo, @Param("twoWeekAgo") Date twoWeekAgo);

    @Query("select avg(s.neutral) from Sentiment s where :username = s.member.username and :aWeekAgo >= s.date and :twoWeekAgo <= s.date")
    double getNeutral(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo, @Param("twoWeekAgo") Date twoWeekAgo);

    @Query("select avg(s.negative) from Sentiment s where :username = s.member.username and :aWeekAgo >= s.date and :twoWeekAgo <= s.date")
    double getNegative(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo, @Param("twoWeekAgo") Date twoWeekAgo);

    @Query("select avg(s.positive) from Sentiment s where :username = s.member.username and :aWeekAgo >= s.date and :twoWeekAgo <= s.date")
    double getPositive(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo, @Param("twoWeekAgo") Date twoWeekAgo);

    @Query("select s.content from Sentiment s where :username = s.member.username and :aWeekAgo <= s.date order by s.negative DESC limit 5")
    List<String> getNegData(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo);

    @Query("select s.content from Sentiment s where :username = s.member.username and :aWeekAgo <= s.date order by s.positive DESC limit 5")
    List<String> getPosData(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo);
}
