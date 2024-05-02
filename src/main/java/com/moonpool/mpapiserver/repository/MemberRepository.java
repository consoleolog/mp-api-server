package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m.username FROM Member m WHERE m.username =:username")
    String CustomfindByUsername(@Param("username") String username);

    Optional<Member> findByUsername(String username);

    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("SELECT m FROM Member m WHERE m.username=:username")
    Member getWithRoles(@Param("username")String username);
}
