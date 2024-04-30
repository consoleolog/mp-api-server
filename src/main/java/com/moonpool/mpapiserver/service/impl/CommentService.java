package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.CommentDto;
import jakarta.transaction.Transactional;

import java.util.Map;

@Transactional
public interface CommentService {

    void register(CommentDto commentDto);
    Map<String,Object> getList(Long id ,Long problemId);
}
