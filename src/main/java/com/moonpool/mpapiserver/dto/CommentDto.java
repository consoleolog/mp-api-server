package com.moonpool.mpapiserver.dto;

import com.moonpool.mpapiserver.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long writerId;
    private Long parentId;

    public Comment toEntity(CommentDto commentDto){
        return Comment.builder()
                .content(commentDto.getContent())
                .parentId(commentDto.getParentId())
                .writerId(commentDto.getWriterId())
                .build();
    }
}
