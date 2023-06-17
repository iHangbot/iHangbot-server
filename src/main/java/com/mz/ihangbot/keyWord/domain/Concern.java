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
public class Concern extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concern_id")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double confidence;

    @Column(nullable = false)
    private Date date;

    @Builder
    public Concern(Member member, String category, double confidence, Date date) {
        this.member = member;
        this.category = category;
        this.confidence = confidence;
        this.date = date;
    }
}
