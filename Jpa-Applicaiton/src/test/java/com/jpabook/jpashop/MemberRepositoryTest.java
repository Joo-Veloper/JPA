//package com.jpabook.jpashop;
//
//
//import com.jpabook.jpashop.domain.Member;
//import com.jpabook.jpashop.service.MemberService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired
//    MemberService memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false) //롤백 제외 -> 커밋 실행함
//    public void testMember() throws Exception{
//        // given
//        Member member = new Member();
//        member.setName("memberA");
//
//        // when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
//        Assertions.assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember == member:" + (findMember == member));
//    }
//}