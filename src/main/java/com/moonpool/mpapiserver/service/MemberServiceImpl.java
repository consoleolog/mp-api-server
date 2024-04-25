package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.MemberDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
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



}
