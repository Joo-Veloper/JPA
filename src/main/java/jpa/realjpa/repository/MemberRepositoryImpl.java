package jpa.realjpa.repository;

import jakarta.persistence.EntityManager;
import jpa.realjpa.entity.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
// MemberRepository(레포지토리 이름) + impl(고정)
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
