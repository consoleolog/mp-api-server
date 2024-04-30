package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Problem;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Transactional
public interface ProblemService {
    Object getOne(Long id);
    void post(ProblemDto problemDto) throws IOException;
    Map<String, Object> getList(Long id, String category);

    void modify(ProblemDto problemDto) throws IOException;
}
