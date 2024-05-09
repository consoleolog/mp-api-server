package com.moonpool.mpapiserver.service.impl;

import com.moonpool.mpapiserver.dto.CartDto;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface CartService {
    Boolean register(CartDto cartDto);

    List<?> getList(Long id);

    void delete(Long id);

    void deleteAll(Long id);
}
