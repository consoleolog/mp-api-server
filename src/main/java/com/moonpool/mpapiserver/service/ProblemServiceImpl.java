package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.AnswerDto;
import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.handler.FileHandler;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.service.impl.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;

    @Override
    public Object getOne(Long id){
        Optional<?> result = problemRepository.findById(id);
        var problem = result.orElseThrow();
        return problem;
    }
    @Override
    public void post(ProblemDto problemDto) throws IOException {
        String quizImgName = fileHandler.saveFile(problemDto.getQuizImgFile());
        String answerImgName = fileHandler.saveFile(problemDto.getAnswerImgFile());
        Optional<Member> result = memberRepository.findById(problemDto.getWriterId());
        Problem problem = Problem.builder()
                .id(problemDto.getId())
                .title(problemDto.getTitle())
                .price(problemDto.getPrice())
                .description(problemDto.getDescription())
                .category(problemDto.getCategory())
                .level(problemDto.getLevel())
                .quizImgName(quizImgName)
                .answerImgName(answerImgName)
                .answer(problemDto.getAnswer())
                .writerId(problemDto.getWriterId())
                .build();
        problemRepository.save(problem);
    }

    @Override
    public Map<String,Object> getList(Long id ,String category){
        List<?> problemList = problemRepository.findByCategory(category, Math.toIntExact(id));
        int end = (int) (Math.ceil(id.intValue()/10.0)) * 10; // 20
        int start = end - 9;
        Long totalCount = problemRepository.countByCategory(category);
        int last = (int)(Math.ceil((double)totalCount/(double)10.0));
        end = end > last ? end : last;
        start = start < 1 ? 1 : start;
        List<Integer> numList = IntStream.rangeClosed(start, end).boxed().toList();
        Map<String, Object> result = new HashMap<>();
        result.put("problemList", problemList);
        result.put("numList", numList);
        result.put("end", end);
        result.put("start", start);
        return result;
    }
    @Override
    public void modify(ProblemDto problemDto) throws IOException {
        // 일단 조회
        Optional<Problem> result = problemRepository.findById(problemDto.getId());
        Problem problem = result.orElseThrow();
        String quizImgName;
        String answerImgName;
        //변경 내용 반영
        problem.changeTitle(problemDto.getTitle());
        problem.changePrice(problemDto.getPrice());
        problem.changeDescription(problemDto.getDescription());
        problem.changeCategory(problemDto.getCategory());
        problem.changeLevel(problemDto.getLevel());
        problem.changeAnswer(problemDto.getAnswer());
        if (problemDto.getQuizImgFile() != null){
            quizImgName = fileHandler.saveFile(problemDto.getQuizImgFile());
            problem.changeQuizImgName(quizImgName);
        }
        if (problemDto.getAnswerImgFile() != null){
            answerImgName = fileHandler.saveFile(problemDto.getAnswerImgFile());
            problem.changeAnswerImgName(answerImgName);
        }
        problemRepository.save(problem);
    }
    @Override
    public Boolean checkAnswer(AnswerDto answerDto){
        Optional<Problem> pResult = problemRepository.findById(answerDto.getProblemId());
        Problem problem = pResult.orElseThrow();
        Integer answer = problem.getAnswer();
        Optional<Member> mResult = memberRepository.findById(answerDto.getMemberId());
        Member member = mResult.orElseThrow();
        if (Objects.equals(answer, answerDto.getAnswer())){
            member.changeCoin(member.getCoin() + 100);
            memberRepository.save(member);
            return true;
        }
        return false;
    }
}
