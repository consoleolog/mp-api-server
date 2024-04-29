package com.moonpool.mpapiserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, unique = true , nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 30)
    private String displayName;

    @Column(length = 100)
    private String intro;

    @Column(length = 30, nullable = false)
    private String educationState;

    @Builder.Default
    private Integer coin = 1000;

//    @ToString.Exclude
//    @Builder.Default
//    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
//    List<Problem> problems = new ArrayList<>();

    public void changeDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeIntro(String intro) {
        this.intro = intro;
    }

    public void changeEducationState(String educationState) {
        this.educationState = educationState;
    }
}
