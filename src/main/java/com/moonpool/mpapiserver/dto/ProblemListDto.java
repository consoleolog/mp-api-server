package com.moonpool.mpapiserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProblemListDto {
    List<Long> problemIdList;
    Long memberId;
}
