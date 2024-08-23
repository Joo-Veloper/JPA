package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Test
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("PARK");

        // when
        Long savedId = memberService.join(member);

        // then
//        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_확인_예약() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("Park");

        Member member2 = new Member();
        member2.setName("Park");

        // when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}