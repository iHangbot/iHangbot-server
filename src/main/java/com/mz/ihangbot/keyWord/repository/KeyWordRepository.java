package com.mz.ihangbot.keyWord.repository;

import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.keyWord.dto.KeyWordReportResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeyWordRepository extends JpaRepository<KeyWord, Long>  {

    @Query("select new com.mz.ihangbot.keyWord.dto.KeyWordReportResponseDTO(result.kw, result.cnt) from (select k.keyword as kw, SUM(k.count) as cnt from KeyWord k group by k.keyword) as result order by result.cnt DESC limit 5")
    List<KeyWordReportResponseDTO> getKeyWords();
}
