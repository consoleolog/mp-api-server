package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.ProblemListDto;
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
    public Boolean purchase(SalesDto salesDto) throws Exception {
        List<Long> salesList = salesRepository.findPidListAllByMemberId(salesDto.getMemberId());
        for (Long salesPid:salesList){
            if(salesPid.equals(salesDto.getProblemId())){
                return false;
            }
        }
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
        return true;
    }
    public Problem getAnswer(Long problemId){
        return salesRepository.findByProblemId(problemId);
    }
    public Object getOne(SalesDto salesDto){
         //문제 아이디가 필요할것같은ㄷ
        Optional<?> result = salesRepository.findAnswerByProblemIdAndMemberId(salesDto.getProblemId(),salesDto.getMemberId());
        Object sales = result.orElseThrow();
        return  sales;
    }
    public List getList(Long memberId){
        List<?> result = salesRepository.findAllByMemberId(memberId);
        return result;
    }
    public Boolean purchaseAll(ProblemListDto problemListDto){
        for (Long id:problemListDto.getProblemIdList()){
            Sales sales = Sales.builder()
                    .problemId(id)
                    .memberId(problemListDto.getMemberId())
                    .build();
            salesRepository.save(sales);
            Optional<Problem> problemResult = problemRepository.findById(id);
            Problem problem = problemResult.orElseThrow();
            Optional<Member> memberResult = memberRepository.findById(problemListDto.getMemberId());
            Member member = memberResult.orElseThrow();
            try {
                member.changeCoin(member.getCoin() - problem.getPrice());
                if(member.getCoin() < 0){
                    return false;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
