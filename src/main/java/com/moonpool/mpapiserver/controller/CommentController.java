package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.CommentDto;
import com.moonpool.mpapiserver.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/mp/comments")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CommentDto commentDto){
        commentService.register(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글 등록 완료");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getList(@PathVariable("id")Long id,
                                    @RequestParam("parentId")Long problemId){
        Map<String,Object> result = commentService.getList(id, problemId);
        return ResponseEntity.ok(result);
    }
}
