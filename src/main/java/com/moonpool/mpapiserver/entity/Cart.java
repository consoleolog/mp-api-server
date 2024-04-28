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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    public CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .problemId(cart.getProblem().getId())
                .ownerId(cart.getMember().getId())
                .build();
    }
}