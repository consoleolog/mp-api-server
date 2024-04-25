package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.problem.id =:id")
    List<String> findAllByParentId(@Param("id") Long id);
}
