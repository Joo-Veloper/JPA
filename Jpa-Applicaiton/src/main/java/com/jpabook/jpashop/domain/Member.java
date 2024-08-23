package com.jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty // 값이 필수로 들어감
    private String name;

    @Embedded // 내장타입
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") // 연관관계의 주인이 아닌 거울이면 mappedBy 사용
    private List<Order> orders = new ArrayList<>();
}
