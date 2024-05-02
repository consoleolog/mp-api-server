package com.moonpool.mpapiserver.controller;

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
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody SalesDto salesDto) throws Exception {
        log.info(salesDto);
        String result = salesService.purchase(salesDto);
        log.info(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/items")
    public ResponseEntity<?> getOne(@RequestBody SalesDto salesDto){
        var result = salesService.getOne(salesDto);
        return ResponseEntity.status(200).body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/items/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable("id")Long id){
        var result = salesService.getAnswer(id);
        return ResponseEntity.status(200).body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/member-id/{id}")
    public ResponseEntity<List> getList(@PathVariable("id")Long id){
        List<?> result = salesService.getList(id);
        return ResponseEntity.status(200).body(result);
    }
}
