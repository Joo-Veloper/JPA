package com.jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")

    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    // EnumType.ORDINAL을 사용하는 것이 권장되지 않는 주된 이유는, 열거형 상수의 순서가 변경되거나 중간에 새로운 상수가 추가될 경우 데이터베이스에 저장된 값이 더 이상 정확한 열거형 값을 반영하지 못하기 때문입니다.
    private DeliveryStatus status; // READY, COMP
}
