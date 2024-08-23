package com.jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // setter제거 값타입은 변경 불가능하게 설계 생성자에서 값을 모두 초기화해서 변경불가능한 클래스를 만드는게 좋음
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
