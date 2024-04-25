package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.UserDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isEmpty()){
            throw new UsernameNotFoundException(username+"라는 아이디 없음");
        }
        Member user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDto userDto = new UserDto(user.getUsername(), user.getPassword(), authorities);
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.intro = user.getIntro();
        userDto.displayName = user.getDisplayName();
        userDto.educationState = user.getEducationState();
        userDto.coin = user.getCoin();
        return userDto;
    }
}
