package com.moonpool.mpapiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProblemDto {
    private Long id;
    private String title;
    private Integer price;
    private String description;
    private String category;
    private String level;
    private MultipartFile quizImgFile;
    private List<MultipartFile> answerImgFiles;
    private Integer answer;
    private Long writerId;

}
