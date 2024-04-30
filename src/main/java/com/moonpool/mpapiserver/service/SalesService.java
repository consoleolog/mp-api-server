package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.SalesDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.entity.Sales;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final ProblemRepository problemRepository;
    private final MemberRepository memberRepository;
    public String purchase(SalesDto salesDto) throws Exception {
        Sales result = salesRepository.save(salesDto.toEntity(salesDto));
        Optional<Problem> problem = problemRepository.findById(salesDto.getProblemId());
        Problem answer = problem.orElseThrow();
        Integer price = answer.getPrice();
        try {
            Optional<Member> member = memberRepository.findById(result.getMemberId());
            Member requestUser = member.orElseThrow();
            requestUser.changeCoin(requestUser.getCoin() - price);
            memberRepository.save(requestUser);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return answer.getAnswerImgName();
    }
    public void getOne(){
        //문제 아이디가 필요할것같은ㄷ[
    }
}
