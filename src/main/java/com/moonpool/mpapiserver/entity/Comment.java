package com.moonpool.mpapiserver.entity;

import com.moonpool.mpapiserver.dto.CommentDto;
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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "writer_id",
//            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id",
//            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private Problem problem;
    private Long writerId;
    private Long parentId;

    public CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writerId(comment.getWriterId())
                .parentId(comment.getParentId())
                .build();
    }
    public void changeContent(String content){
        this.content = content;
    }
}