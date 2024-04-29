package com.moonpool.mpapiserver.controller;


import com.moonpool.mpapiserver.dto.MemberDto;
import com.moonpool.mpapiserver.service.impl.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mp/members")
@RestController
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("")
    public ResponseEntity<String> getMainPage(){
        return ResponseEntity.ok("/");
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);
        memberService.register(memberDto);
//        System.out.println(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");
    }
//    @PostMapping("/modify")
//    public ResponseEntity<String> modify(@RequestBody MemberDto memberDto){
//        memberService.modify(memberDto);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원정보 수정 완료");
//    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Long id){
//        memberService.delete(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("회원 탈퇴 완료");
//    }
    @GetMapping("/user-detail-data")
    public ResponseEntity<?> getUserInfo(Authentication auth) throws NullPointerException{
//        Map<String, Object> result = memberService.userInfo(auth);
        log.info(auth);
        return ResponseEntity.ok("");
    }
}
