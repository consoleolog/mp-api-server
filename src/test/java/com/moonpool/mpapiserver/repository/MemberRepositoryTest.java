package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void resgisterTest(){
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .id(null)
                    .username("test"+i+"@test.com")
                    .password(passwordEncoder.encode("1111"))
                    .displayName("test user"+i)
                    .intro("test user intro..."+i+".................")
                    .educationState("university")
                    .build();
            memberRepository.save(member);
        }
    }
    @Test
    public void testInsertMember(){
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .username("user"+i+10+"@test.com")
                    .password(passwordEncoder.encode("1111"))
                    .intro("intro.......")
                    .displayName("testUser"+i+10)
                    .educationState("university")
                    .build();
            member.addRole(MemberRole.USER);
            if ( i > 5 ){
                member.addRole(MemberRole.MANAGER);
            }
            if (i > 8){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        }

    }
}
