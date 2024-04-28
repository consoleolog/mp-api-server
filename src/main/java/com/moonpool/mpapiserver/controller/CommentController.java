package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.CommentDto;
import com.moonpool.mpapiserver.entity.Comment;
import com.moonpool.mpapiserver.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mp/comments")
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List> getList(@PathVariable("id") Long id){
        List<Comment> result = commentService.getList(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CommentDto commentDto){
        commentService.register(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글 등록 완료");
    }
    @PostMapping("/modify")
    public ResponseEntity<String> modify(@RequestBody CommentDto commentDto){
        commentService.modify(commentDto);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 수정 완료");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 완료");
    }


}
