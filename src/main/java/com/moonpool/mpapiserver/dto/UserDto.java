package com.moonpool.mpapiserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class UserDto extends User {
    public Long id;
    public String username;
    public String intro;
    public String displayName;
    public String educationState;
    public Integer coin;
    public UserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}