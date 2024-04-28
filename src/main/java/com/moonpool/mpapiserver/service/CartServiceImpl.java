package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.CartDto;
import com.moonpool.mpapiserver.entity.Cart;
import com.moonpool.mpapiserver.entity.Member;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.repository.CartRepository;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProblemRepository problemRepository;
    public void register(CartDto cartDto){
        Optional<Member> memberResult = memberRepository.findById(cartDto.getOwnerId());
        Member member = memberResult.orElseThrow();
        member.getId();
        Optional<Problem> problemResult = problemRepository.findById(cartDto.getProblemId());
        Problem problem = problemResult.orElseThrow();
        problem.getId();
        Cart cart = Cart.builder()
                .id(cartDto.getId())
                .problem(problem)
                .member(member)
                .build();
        cartRepository.save(cart);
    }
    public List<Cart> getList(Long id){
        return cartRepository.findAllByOwnerId(id);
    }
    public void delete(Long id){
        cartRepository.deleteById(id);
    }
}
