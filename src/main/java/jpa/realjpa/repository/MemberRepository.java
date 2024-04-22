package jpa.realjpa.repository;

import jpa.realjpa.dto.MemberDto;
import jpa.realjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

//    @Query(name = "Member.findByUsername") 없어도 됨!
    List<Member> findByUsername(@Param("username") String username);

    // 복잡한 JPQL 바로 넣어 사용 가능
    // 동적 쿼리는 QueryDSL 사용
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //JPQL이 제공하는 문법 new Operation
    // DTO 반환은 가능하지만 경로를 적어줘야한다는 단점..
    @Query("select new jpa.realjpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
