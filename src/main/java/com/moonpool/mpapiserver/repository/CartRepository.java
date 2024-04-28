package com.moonpool.mpapiserver.repository;

import com.moonpool.mpapiserver.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    // 회원 아이디를 조회해서 장바구니에 있는 아이템들을 싹다 가져와야됨

    @Query(value = "SELECT c FROM Cart c JOIN FETCH c.problem WHERE c.member.id=:id")
    List<Cart> findAllByOwnerId(@Param("id") Long id);
}