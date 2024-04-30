package com.moonpool.mpapiserver.dto;

import com.moonpool.mpapiserver.entity.Sales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesDto {
    private Long id;
    private Long problemId;
    private Long memberId;

    public Sales toEntity(SalesDto salesDto){
        return Sales.builder()
                .problemId(salesDto.getProblemId())
                .memberId(salesDto.getMemberId())
                .build();
    }
}
