package jpa.realjpa.repository;

import jpa.realjpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();

}
