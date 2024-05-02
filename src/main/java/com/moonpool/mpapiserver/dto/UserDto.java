package com.moonpool.mpapiserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
public class UserDto extends User {
    private Long id;
    private String username;
    private String password;
    private String intro;
    private String displayName;
    private String educationState;
    private Integer coin;

    private List<String> roleNames = new ArrayList<>();

    public UserDto(Long id, String username, String password, String intro, String displayName, String educationState, Integer coin,List<String> roleNames) {
        super(username,
                password,
                roleNames.stream().map(str->new SimpleGrantedAuthority("ROLE"+str)).collect(Collectors.toList()));
        this.id = id;
        this.username = username;
        this.password = password;
        this.intro = intro;
        this.displayName = displayName;
        this.educationState = educationState;
        this.coin = coin;
        this.roleNames = roleNames;
    }
    public Map<String, Object> getClaims(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", id);
        dataMap.put("username", username);
        dataMap.put("password",password);
        dataMap.put("intro",intro);
        dataMap.put("displayName",displayName);
        dataMap.put("educationState",educationState);
        dataMap.put("coin",coin);
        dataMap.put("roleNames",roleNames);
        return dataMap;
    }
}