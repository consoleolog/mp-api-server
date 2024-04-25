package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Problem;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.Map;

@Transactional
public interface ProblemService {
    Problem getOne(Long id);
    void post(ProblemDto problemDto);
    Map<String, Object> getList(Long id, String category);

}
