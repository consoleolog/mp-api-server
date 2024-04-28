package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.CartDto;
import com.moonpool.mpapiserver.entity.Cart;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface CartService {
    void register(CartDto cartDto);

    List<Cart> getList(Long id);

    void delete(Long id);
}
