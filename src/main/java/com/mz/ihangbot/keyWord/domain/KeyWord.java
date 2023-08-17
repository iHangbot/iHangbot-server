package com.mz.ihangbot.keyWord.domain;

import com.mz.ihangbot.common.domain.DateTimeEntity;
import com.mz.ihangbot.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Getter
@NoArgsConstructor
public class KeyWord extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private Date date;

    @Builder
    public KeyWord(Member member, String keyword, int count, Date date) {
        this.member = member;
        this.keyword = keyword;
        this.count = count;
        this.date = date;
    }

    public void updateCount(int count) {
        this.count += count;
    }
}
