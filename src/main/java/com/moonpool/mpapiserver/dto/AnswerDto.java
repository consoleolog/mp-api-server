package com.moonpool.mpapiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerDto {
    private Long problemId;
    private Long memberId;
    private Integer answer;
}
