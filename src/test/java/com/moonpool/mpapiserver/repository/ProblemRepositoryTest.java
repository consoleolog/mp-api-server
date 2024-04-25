package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class ProblemRepositoryTest {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void postTest(){
        Optional<Member> result = memberRepository.findById(1L);
        Member member = result.orElseThrow();
        member.getId();
        for (int i = 0; i < 100; i++) {
            Problem problem = Problem.builder()
                    .id(null)
                    .title("problem title"+i+".......")
                    .price(100 + i)
                    .description("problem description"+i+".......")
                    .category("korean")
                    .level("hard")
                    .quizImgName("Q_testimage")
                    .answer(i)
                    .member(member)
                    .build();
            problemRepository.save(problem);
        }
    }
    @Test
    public void getListTest(){
        List<Problem> result = problemRepository.findByCategory("korean", 0);
        System.out.println(result);

    }
}
