package com.moonpool.mpapiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProblemResponseDto {
    private Long id;
    private String title;
    private Integer price;
    private String category;
    private String description;
    private String level;
    private String quizImgName;
    private Long writerId;
}
