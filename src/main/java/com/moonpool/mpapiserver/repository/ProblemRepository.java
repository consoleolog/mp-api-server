package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProblemRepository extends JpaRepository<Problem, Long> {

//    @Query(value = "SELECT p FROM Problem as p WHERE p.category = ?1 ORDER BY p.problem_id DESC LIMIT 10 OFFSET ?2 ", nativeQuery = true)
//    List<Problem> findByCategoryAndId(String category, Long id);
//@EntityGraph(attributePaths = {"memeber"})
@Query(value = "SELECT p.id, p.title, p.price, p.category, p.description, p.level, p.quizImgName, p.writerId" +
        " FROM Problem p  " +
        " WHERE p.category = :category " +
        " ORDER BY p.id DESC " +
        " LIMIT 10 OFFSET :offset")
List<?> findByCategory(@Param("category") String category, @Param("offset") int offset);

@Query(value = "SELECT * FROM Problem as p WHERE to_tsvector('english',p.title) @@ to_tsquery('korean', p.title= ?1 )",nativeQuery = true)
List<?> findAllByTitle(String title);

@Query(value = "SELECT COUNT(p) FROM Problem p WHERE p.category=:category")
Long countByCategory(@Param("category") String category);
}
