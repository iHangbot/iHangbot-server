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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String child_name;

    @Column(nullable = false)
    private int child_age;

    private String phone_num;

    private double positive;

    private double negative;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public Member(String email, String password, String child_name, int child_age, String phone_num, double positive, double negative) {
        this.email = email;
        this.password = password;
        this.child_name = child_name;
        this.child_age = child_age;
        this.phone_num = phone_num;
        this.positive = positive;
        this.negative = negative;
    }

}
