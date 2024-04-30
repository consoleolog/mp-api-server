package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "SELECT c , m " +
            " FROM Comment c " +
            " INNER JOIN Member m " +
            " ON c.writerId = m.id" +
            " WHERE c.parentId=:problemId " +
            " ORDER BY c.id DESC " +
            " LIMIT 10 OFFSET :offset")
    List<?> findByParentId(@Param("problemId")Long problemId, @Param("offset") int offset);

    @Query(value = "SELECT COUNT(c) FROM Comment c WHERE c.parentId=:parentId")
    Long countByParentId(@Param("parentId")Long parentId);

}
