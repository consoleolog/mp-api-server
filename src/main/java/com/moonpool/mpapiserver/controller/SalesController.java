package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.ProblemListDto;
import com.moonpool.mpapiserver.dto.SalesDto;
import com.moonpool.mpapiserver.entity.Sales;
import com.moonpool.mpapiserver.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mp/sales")
@RestController
public class SalesController {
    private final SalesService salesService;
    @PostMapping("/purchase")
    public ResponseEntity<Boolean> purchase(@RequestBody SalesDto salesDto) throws Exception {
        Boolean result = salesService.purchase(salesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/items")
    public ResponseEntity<?> getOne(@RequestBody SalesDto salesDto){
        var result = salesService.getOne(salesDto);
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable("id")Long id){
        var result = salesService.getAnswer(id);
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping("/member-id/{id}")
    public ResponseEntity<List> getList(@PathVariable("id")Long id){
        List<?> result = salesService.getList(id);
        return ResponseEntity.status(200).body(result);
    }
    @PostMapping("/purchase/all")
    public ResponseEntity<?> purcahseList(@RequestBody ProblemListDto problemListDto){
        var result = salesService.purchaseAll(problemListDto);
        return ResponseEntity.ok(result);
    }
}
