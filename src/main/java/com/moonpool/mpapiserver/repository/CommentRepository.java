package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment c WHERE c.parentId =:id")
    Page<?> listOfProblem(@Param("id")Long id, Pageable pageable);

    void deleteByProblemId(Long id);

    @Query(value = "SELECT c FROM Comment c WHERE c.parentId =:parentId ORDER BY c.id DESC LIMIT 15 OFFSET :offset")
    List<?> findByProblemId(@Param("parentId")Long parentId,@Param("offset") int offset);
//    @Query(value = "SELECT c FROM Comment c JOIN FETCH c.member WHERE c.problem.id =:id")
//    List<Comment> findAllByParentId(@Param("id") Long id, Sort sort);



}
