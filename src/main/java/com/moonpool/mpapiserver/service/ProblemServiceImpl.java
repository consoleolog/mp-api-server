package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.ProblemDto;
import com.moonpool.mpapiserver.entity.Answer;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.handler.FileHandler;
import com.moonpool.mpapiserver.repository.AnswerRepository;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.service.impl.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;
    private final AnswerRepository answerRepository;

    @Override
    public Problem getOne(Long id){
        Optional<Problem> result = problemRepository.findById(id);
        Problem problem = result.orElseThrow();
        return problem;
    }
    @Override
    public void post(ProblemDto problemDto){
        String quizImgName = fileHandler.saveQuizFile(problemDto.getQuizImgFile());
        List<String> answerImgNames = fileHandler.saveAnswerFiles(problemDto.getAnswerImgFiles());
        Optional<Member> result = memberRepository.findById(problemDto.getWriterId());
        Member member = result.orElseThrow();
        member.getId();
        Problem problem = Problem.builder()
                .id(problemDto.getId())
                .title(problemDto.getTitle())
                .price(problemDto.getPrice())
                .description(problemDto.getDescription())
                .category(problemDto.getCategory())
                .level(problemDto.getLevel())
                .quizImgName(quizImgName)
                .answer(problemDto.getAnswer())
                .member(member)
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

    public void modify(ProblemDto problemDto){
        // 일단 조회
        Optional<Problem> result = problemRepository.findById(problemDto.getId());
        Problem problem = result.orElseThrow();
        //변경 내용 반영
        problem.changeTitle(problemDto.getTitle());
        problem.changePrice(problemDto.getPrice());
        problem.changeDescription(problemDto.getDescription());
        problem.changeCategory(problemDto.getCategory());
        problem.changeLevel(problemDto.getLevel());
        problem.changeAnswer(problemDto.getAnswer());


        // 이미지
        List<String> answerImgNames = answerRepository.findAllByParentId(problemDto.getId());
        fileHandler.deleteFiles(answerImgNames);

        //저장


    }

}
