package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.CommentDto;
import com.moonpool.mpapiserver.entity.Comment;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.repository.CommentRepository;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ProblemRepository problemRepository;

    public List<?> getList(Long id){
//        commentRepository.findAllByParentId(id,PageRequest.of(1,10, Sort.by("comment_id").descending()));
        //id 가 문제 아이디임
        Sort sort = Sort.by(Sort.Direction.DESC, "comment_id"); // 내림차순으로 commentId 기준 정렬

        return null;
    }

    public void register(CommentDto commentDto){
        Optional<Member> memberResult = memberRepository.findById(commentDto.getWriterId());
        Member member = memberResult.orElseThrow();
        member.getId();
        Optional<Problem> problemResult = problemRepository.findById(commentDto.getParentId());
        Problem problem = problemResult.orElseThrow();
        problem.getId();
        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .member(member)
                .problem(problem)
                .build();
        commentRepository.save(comment);
    }
    public void modify(CommentDto commentDto){
        Optional<Comment> result = commentRepository.findById(commentDto.getId());
        Comment comment = result.orElseThrow();
        comment.changeContent(commentDto.getContent());
        commentRepository.save(comment);
    }
    public void delete(Long id){
        commentRepository.deleteById(id);
    }


}