package com.moonpool.mpapiserver.entity;

import com.moonpool.mpapiserver.dto.CartDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private Long problemId;
    private Long ownerId;

    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .problemId(cart.getProblemId())
                .ownerId(cart.getOwnerId())
                .build();
    }
}