package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.handler.FileHandler;
import com.moonpool.mpapiserver.service.impl.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/mp/problems")
@RestController
public class ProblemController {
    private final FileHandler fileHandler;
    private final ProblemService problemService;
    @GetMapping("/{category}/{id}")
    public ResponseEntity<Map> getList(@PathVariable("category")String category,
                                     @PathVariable("id")Long id){
        Map<String,Object> result = problemService.getList(id, category);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        Problem result = problemService.getOne(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/post")
    public ResponseEntity<String> post(ProblemDto problemDto){
        problemService.post(problemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("문제 등록 완료");
    }
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable("fileName")String fileName){
        return fileHandler.getFile(fileName);
    }



}
