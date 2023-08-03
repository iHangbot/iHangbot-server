package com.mz.ihangbot.member.domain;

import com.mz.ihangbot.common.domain.DateTimeEntity;
import com.mz.ihangbot.keyWord.domain.KeyWord;
import com.mz.ihangbot.sentiment.domain.Sentiment;
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
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String child_name;

    @Column(nullable = false)
    private int child_age;

    @Column(nullable = false)
    private boolean child_gender;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<KeyWord> keyWords = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<Sentiment> sentiments = new ArrayList<>();

    @Builder
    public Member(String username, String password, String child_name, int child_age, boolean child_gender) {
        this.username = username;
        this.password = password;
        this.child_name = child_name;
        this.child_gender = child_gender;
        this.child_age = child_age;
    }

    public void update(String child_name, int child_age, boolean child_gender) {
        this.child_name = child_name;
        this.child_age = child_age;
        this.child_gender = child_gender;
    }
}
