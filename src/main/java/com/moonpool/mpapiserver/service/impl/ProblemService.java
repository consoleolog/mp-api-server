package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.AnswerDto;
import com.moonpool.mpapiserver.dto.ProblemDto;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Transactional
public interface ProblemService {
    Object getOne(Long id);
    String post(ProblemDto problemDto) throws IOException;
    Map<String, Object> getList(Long id, String category);

    void modify(ProblemDto problemDto) throws IOException;
    void delete(Long id);
    Boolean checkAnswer(AnswerDto answerDto);
    List madeList(Long id);
}
