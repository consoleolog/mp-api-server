package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.AnswerDto;
import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.handler.FileHandler;
import com.moonpool.mpapiserver.service.impl.ProblemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
@Log4j2
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
        Object result = (Problem) problemService.getOne(id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id")Long id){
        problemService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }
    @PostMapping("/post")
    public ResponseEntity<String> post(ProblemDto problemDto) throws IOException {
        String result = problemService.post(problemDto);
//        log.info(problemDto.getAnswerImgFile().getOriginalFilename());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable("fileName")String fileName){
        return fileHandler.getFile(fileName);
    }
    @PostMapping("/modify")
    public ResponseEntity<?> modify(ProblemDto problemDto) throws IOException {
        log.info(problemDto);
        problemService.modify(problemDto);
        return ResponseEntity.status(200).body("수정 완료");
    }
    @PostMapping("/check/answer")
    public ResponseEntity<Boolean> checkAnswer(@RequestBody AnswerDto answerDto){
        Boolean result = problemService.checkAnswer(answerDto);
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping("/made/{id}")
    public ResponseEntity<?> getMadeList(@PathVariable("id")Long id){
        var result = problemService.madeList(id);
        return ResponseEntity.ok(result);
    }
}
