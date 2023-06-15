package com.mz.ihangbot.member.domain;

import com.mz.ihangbot.common.domain.DateTimeEntity;
import com.mz.ihangbot.Report.domain.Report;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
public class Member extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String child_name;

    @Column(nullable = false)
    private int child_age;

    @Column(nullable = false)
    private boolean child_gender;

    private double positive;

    private double negative;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(String user_id, String email, String password, String child_name, int child_age, boolean child_gender, double positive, double negative) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.child_name = child_name;
        this.child_gender = child_gender;
        this.child_age = child_age;
        this.positive = positive;
        this.negative = negative;
    }

    public void update(String child_name, int child_age, boolean child_gender, String email) {
        this.child_name = child_name;
        this.child_age = child_age;
        this.child_gender = child_gender;
        this.email = email;
    }
}
