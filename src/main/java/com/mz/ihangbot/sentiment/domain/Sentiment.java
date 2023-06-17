package com.mz.ihangbot.sentiment.domain;

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
public class Sentiment extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double negative;

    @Column(nullable = false)
    private double positive;

    @Column(nullable = false)
    private double neutral;

    @Column(nullable = false)
    private Date date;

    @Builder
    public Sentiment(Member member, String content, double negative, double positive, double neutral, Date date) {
        this.member = member;
        this.content = content;
        this.negative = negative;
        this.positive = positive;
        this.neutral = neutral;
        this.date = date;
    }
}
