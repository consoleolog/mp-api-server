package com.moonpool.mpapiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String displayName;
    private String intro;
    private String educationState;
    private Integer coin;
}
