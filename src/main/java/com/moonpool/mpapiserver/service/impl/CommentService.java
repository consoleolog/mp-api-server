package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.CommentDto;
import com.moonpool.mpapiserver.entity.Comment;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface CommentService {
    List<?> getList(Long id);
    void register(CommentDto commentDto);
    void modify(CommentDto commentDto);
    void delete(Long id);
}