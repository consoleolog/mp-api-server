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
        Cart cart = Cart.builder()
                .id(cartDto.getId())
                .problemId(cartDto.getProblemId())
                .ownerId(cartDto.getOwnerId())
                .build();
        cartRepository.save(cart);
    }
    public List<?> getList(Long id){
        return cartRepository.findAllByOwnerId(id);
    }
    public void delete(Long id){
        cartRepository.deleteById(id);
    }
}
