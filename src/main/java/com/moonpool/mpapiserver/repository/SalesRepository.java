package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Long> {
    @Query("SELECT p FROM Problem p INNER JOIN Sales s ON s.problemId=p.id WHERE s.memberId=:memberId AND s.problemId=:problemId")
    Optional<?> findAnswerByProblemIdAndMemberId(@Param("problemId")Long problemId,@Param("memberId")Long memberId);

    @Query("SELECT  p FROM Problem p INNER JOIN Sales s ON s.problemId=p.id WHERE s.memberId=:memberId")
    List<?> findAllByMemberId(@Param("memberId")Long memberId);

    @Query("SELECT  p FROM Problem p INNER JOIN Sales s ON s.problemId=p.id WHERE s.problemId=:problemId")
    Problem findByProblemId(@Param("problemId")Long problemId);

}
