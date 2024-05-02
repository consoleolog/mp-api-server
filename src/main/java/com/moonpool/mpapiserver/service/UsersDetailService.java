package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.UserDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class UsersDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("---------userdetailservice");
        log.info(username);
        Member member = memberRepository.getWithRoles(username);
        if (member == null){
            throw new UsernameNotFoundException("NOT FOUNT");
        }
        log.info(member);
        UserDto userDto = new UserDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getDisplayName(),
                member.getIntro(),
                member.getEducationState(),
                member.getCoin(),
                member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));
        log.info("--------userdetailservice end-------------");
        log.info(userDto);
        return userDto;
    }
}

