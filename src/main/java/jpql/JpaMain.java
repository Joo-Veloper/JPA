package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);
            member.setAge(10);
            em.persist(member);

            //TypeQuery = 반환 값이 명확할 때 사용
            // Query = 반환 타입이 명확하지 않을 때 사용
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            // 타입 정보를 명기할 수 없을때
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            //타입 정보를 받을 수 없을때
            Query query3 = em.createQuery("select m.username from Member m");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}

//JPQL 문법!
// SELECT_절
// FROM_절
// [WHERE_절]
// [GROUPBY_절]
// [HAVING_절]
// [ORDERBY_절]

// update문 = update_절[where_절]
// delete문 = delete_절[where_절]

//select m from Member as m wher m.age>18
// member와 age는 대소문자를 구분해야함!!
// JPQL 키워드 대소문자 구분 XX
// 엔티티 이름 사용 -> @Entity(name = "Member")


