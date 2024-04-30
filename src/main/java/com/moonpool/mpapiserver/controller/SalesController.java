package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.SalesDto;
import com.moonpool.mpapiserver.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mp/sales")
@RestController
public class SalesController {
    private final SalesService salesService;

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody SalesDto salesDto) throws Exception {
        log.info(salesDto);
        String result = salesService.purchase(salesDto);
        log.info(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
