package com.moonpool.mpapiserver.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Problem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long id;

    @Column(length = 50)
    private String title;

    private Integer price;

    @Column(length = 150)
    private String description;

    @Column(length = 10)
    private String category;

    @Column(length = 10)
    private String level;

    private String quizImgName;

    private String answerImgName;

    private Integer answer;

    @Builder.Default
    private Boolean delFlag = false;

//    @ToString.Exclude
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "writer_id",
//    foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private Member member;
    private Long writerId;
//    @ToString.Exclude
//    @Builder.Default
//    @OneToMany(mappedBy = "problem",fetch = FetchType.LAZY)
//    private List<Answer> answers = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeCategory(String category) {
        this.category = category;
    }

    public void changeLevel(String level) {
        this.level = level;
    }

    public void changeQuizImgName(String quizImgName) {
        this.quizImgName = quizImgName;
    }

    public void changeAnswer(Integer answer) {
        this.answer = answer;
    }

    public void changeAnswerImgName(String answerImgName){
        this.answerImgName = answerImgName;
    }

    public void changeDelFlag(Boolean delFlag){
        this.delFlag = true;
    }
}
