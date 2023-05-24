package com.mz.ihangbot.Report.domain;

import com.mz.ihangbot.common.domain.DateTimeEntity;
import com.mz.ihangbot.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Getter
@NoArgsConstructor
public class Report extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Report(Member member) {
        this.member = member;
    }
}
