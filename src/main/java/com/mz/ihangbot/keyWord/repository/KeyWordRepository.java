package com.mz.ihangbot.keyWord.repository;

import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.member.domain.Member;
import com.mz.ihangbot.report.dto.KeyWordReportResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KeyWordRepository extends JpaRepository<KeyWord, Long>  {

    @Query(value = "select new com.mz.ihangbot.report.dto.KeyWordReportResponseDTO(result.kw, result.cnt) from (select k.keyword as kw, SUM(k.count) as cnt from KeyWord k where :username = k.member.username and :aWeekAgo <= k.date group by k.keyword) as result order by result.cnt DESC limit 5")
    List<KeyWordReportResponseDTO> getKeyWords(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo);

    @Query(value = "select k from KeyWord k where :username = k.member.username and k.date >= :aWeekAgo order by k.count DESC limit 5")
    List<KeyWord> findAllByUsernameAndDate(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo);

    @Query(value = "select k from KeyWord k where :username = k.member.username and k.date >= :aWeekAgo and k.keyword = :keyword")
    Optional<KeyWord> findByUsernameAndDateAndKeyWord(@Param("username") String username, @Param("aWeekAgo") Date aWeekAgo, @Param("keyword") String keyword);

}
