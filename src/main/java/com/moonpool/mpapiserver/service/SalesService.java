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

import java.util.List;
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
    public Problem getAnswer(Long problemId){
        return salesRepository.findByProblemId(problemId);
    }
    public Object getOne(SalesDto salesDto){
         //문제 아이디가 필요할것같은ㄷ
        Long memberId = 11L;
        Optional<?> result = salesRepository.findAnswerByProblemIdAndMemberId(salesDto.getProblemId(),salesDto.getMemberId());
        Object sales = result.orElseThrow();
        return  sales;
    }
    public List getList(Long memberId){
        List<?> result = salesRepository.findAllByMemberId(memberId);
        return result;
    }
}
