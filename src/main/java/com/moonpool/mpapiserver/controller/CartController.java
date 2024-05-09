package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.CartDto;
import com.moonpool.mpapiserver.entity.Cart;
import com.moonpool.mpapiserver.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mp/carts")
@RestController
public class CartController {
    private final CartService cartService;
    @GetMapping("/{id}")
    public ResponseEntity<List> getList(@PathVariable("id")Long id){
        List<?> result = cartService.getList(id);
        log.info("-----------------------cart controller");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody CartDto cartDto){
        Boolean result = cartService.register(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("장바구니 항목 삭제 완료");
    }
    @DeleteMapping("/delete/all/{id}")
    public ResponseEntity<?> deleteAll(@PathVariable("id")Long id){
        log.info("---------------------cart delete ");
        cartService.deleteAll(id);
        return ResponseEntity.status(200).body("장바구니 초기화");
    }
}
