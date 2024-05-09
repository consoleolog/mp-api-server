package com.moonpool.mpapiserver.service;

import com.moonpool.mpapiserver.dto.CartDto;
import com.moonpool.mpapiserver.entity.Cart;
import com.moonpool.mpapiserver.entity.Problem;
import com.moonpool.mpapiserver.repository.CartRepository;
import com.moonpool.mpapiserver.repository.MemberRepository;
import com.moonpool.mpapiserver.repository.ProblemRepository;
import com.moonpool.mpapiserver.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Log4j2
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProblemRepository problemRepository;
    @Override
    public Boolean register(CartDto cartDto){
        //이 아이디가 회원 장바구니에 있는지
        List<Long> cartResult = cartRepository.findAllByMemberId(cartDto.getOwnerId());
        List<Long> salesResult = cartRepository.findSalesListByMemberId(cartDto.getOwnerId());
        for(Long cartPid:cartResult){
            log.info(cartPid);
            if(cartPid.equals(cartDto.getProblemId())){
                return false;
            }
        }
        for (Long salesPid:salesResult){
            if (salesPid.equals(cartDto.getProblemId())){
                return false;
            }
        }
        //장바구니의 아이템 아이디를 조회해 그 아이템아이디로 요청한 유저의 장바구니에 있는지 없는지 확인을 하는거야
        Cart cart = Cart.builder()
                .id(cartDto.getId())
                .problemId(cartDto.getProblemId())
                .ownerId(cartDto.getOwnerId())
                .build();
        cartRepository.save(cart);
        return true;
    }
    @Override
    public List<?> getList(Long id){
        return cartRepository.findAllByOwnerId(id);
    }
    @Override
    public void delete(Long id){
        cartRepository.deleteById(id);
    }
    @Override
    public void deleteAll(Long id){
        cartRepository.deleteByOwnerId(id);
    }
}
