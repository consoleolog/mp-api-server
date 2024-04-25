package com.moonpool.mpapiserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",
    foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Problem problem;

    private String answerName;

    public void changeAnswerName(String answerName) {
        this.answerName = answerName;
    }
}
