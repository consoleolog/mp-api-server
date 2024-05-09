package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Cart;
import com.moonpool.mpapiserver.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    // 회원 아이디를 조회해서 장바구니에 있는 아이템들을 싹다 가져와야됨
    Optional<Problem> findByProblemId(Long id);
    @Query(value = "SELECT c , p  FROM Cart c INNER JOIN Problem p ON c.problemId = p.id WHERE c.ownerId=:id")
    List<?> findAllByOwnerId(@Param("id") Long id);

    @Query(value = "SELECT c.problemId FROM Cart c WHERE c.ownerId=:id")
    List<Long> findAllByMemberId(@Param("id")Long id);

    @Query(value = "SELECT s.problemId FROM Sales s WHERE s.memberId=:id")
    List<Long> findSalesListByMemberId(@Param("id")Long id);

    @Query(value = "DELETE FROM Cart c WHERE c.ownerId=:id")
    void customDeleteAll(@Param("id")Long id);

    void deleteAllByOwnerId(Long id);

    void deleteByOwnerId(Long id);
}