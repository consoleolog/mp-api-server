package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.CommentDto;
import com.moonpool.mpapiserver.entity.Comment;
import com.moonpool.mpapiserver.repository.CommentRepository;
import com.moonpool.mpapiserver.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public void register(CommentDto commentDto){
        Comment result = commentRepository.save(commentDto.toEntity(commentDto));
    }
    @Override
    public Map<String, Object> getList(Long id ,Long problemId){
        List<?> commentList = commentRepository.findByParentId(problemId,Math.toIntExact(id));
        int end = (int) (Math.ceil(id.intValue()/10.0)) * 10;
        int start = end - 9;
        Long totalCount = commentRepository.countByParentId(problemId);
        int last = (int)(Math.ceil((double) totalCount/(double) 10.0));
        end = end > last ? end : last;
        start = start < 1 ? 1 : start;
        List<Integer> numList = IntStream.rangeClosed(start,end).boxed().toList();
        Map<String,Object> result = new HashMap<>();
        result.put("commentList", commentList);
        result.put("numList",numList);
        result.put("end",end);
        result.put("start",start);
        return result;
    }
}
