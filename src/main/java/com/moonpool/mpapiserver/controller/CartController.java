package com.moonpool.mpapiserver.controller;

import com.moonpool.mpapiserver.dto.CartDto;
import com.moonpool.mpapiserver.entity.Cart;
import com.moonpool.mpapiserver.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mp/cart")
@RestController
public class CartController {
    private final CartService cartService;
    @GetMapping("/{id}")
    public ResponseEntity<List> getList(@PathVariable("id")Long id){
        List<Cart> result = cartService.getList(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CartDto cartDto){
        cartService.register(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("장바구니 추가 완료");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("장바구니 항목 삭제 완료");
    }
}
