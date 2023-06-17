package com.mz.ihangbot.keyWord.repository;

import com.mz.ihangbot.keyWord.domain.Concern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcernRepository extends JpaRepository<Concern, Long> {

    @Query("select c.category from Concern c group by c.category order by MAX(c.confidence) DESC limit 3")
    List<String> getConcerns();
}
