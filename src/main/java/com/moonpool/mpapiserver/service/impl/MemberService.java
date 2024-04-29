package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.MemberDto;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Transactional
public interface MemberService {

    void register(MemberDto memberDto);

    Map<String, Object> userInfo(Authentication auth);

    String getStoredPassword(String username);
}
