package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.MemberDto;
import com.moonpool.mpapiserver.dto.UserDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void register(MemberDto memberDto){
        Member member = Member.builder()
                .id(memberDto.getId())
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .displayName(memberDto.getDisplayName())
                .intro(memberDto.getIntro())
                .educationState(memberDto.getEducationState())
                .build();
        memberRepository.save(member);
    }
    public void usernameCheck(String username){
        memberRepository.CustomfindByUsername(username);
    }
    @Override
    public Map<String, Object> userInfo(Authentication auth){
        if (auth == null){
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", null);
            return userData;
        } else {
            UserDto result = (UserDto) auth.getPrincipal();
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", result.getId());
            userData.put("username", result.getUsername());
            userData.put("displayName", result.getDisplayName());
            userData.put("educationState", result.getEducationState());
            userData.put("coin", result.getCoin());
            return userData;
        }
    }
    @Override
    public String getStoredPassword(String username){
        Optional<Member> result = memberRepository.findByUsername(username);
        Member member = result.orElseThrow();
        return member.getPassword();
    }
    public void login(String username, String password){
        Map<String, Object> result = new HashMap<>();
        result.put("username",username);
        result.put("password",password);
        List<Map<String, Object>> loginInfo = new ArrayList<>();
//        loginInfo =
    }
}

