package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

            //flush -> commit, query

            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                    .getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

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



//public class JpaMain {
//
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(new Address("homeCity", "street", "10000"));
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("족발");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
//            member.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("=======================================");
//            // 이 컬렉션들은 지연로딩
//            Member findMember = em.find(Member.class, member.getId());
//            // 조회
////            List<Address> addressHistory = findMember.getAddressHistory();
////            for( Address address : addressHistory) {
////                System.out.println("address = " + address.getCity());
////            }
////
////            Set<String> favoriteFoods = findMember.getFavoriteFoods();
////            for (String favoriteFood : favoriteFoods) {
////                System.out.println("favoriteFood =" + favoriteFood);
////            }
//            // 수정
//            // 값 타입 아래 처럼 사용 XX
//            // findMember.getHomeAddress().setCity("newCity");
//
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
////            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
////            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));
//
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
//}





//public class JpaMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager(); // 예를 들어 고객이 들어와서 행위를 하고 나가 이런걸 할 때 고객이 어떤 상품을 장바구니에 담아라던가 이런걸 할때마다
//        // DB 커넥션을 얻어서 쿼리를 날리고 종료되는 그런 한 일관적인 단위를 할 때마다 이 엔티티 메니저라는 애를 꼭 만들어주어야함!!
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member1= new Member();
//            member1.setUsername("A");
//            Member member2= new Member();
//            member2.setUsername("B");
//            Member member3 = new Member();
//            member3.setUsername("C");
//            System.out.println("==============");
//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);
//            System.out.println("member1 = " + member1.getId());
//            System.out.println("member1 = " + member2.getId());
//            System.out.println("member1 = " + member3.getId());
//            System.out.println("==============");
//
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
//}



// 주의
// 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
// 엔티티 매니저는 쓰레드간에 공유하면 안됨!! 사용하고 버려야함
// JPA의 모든 데이터 변경은 트랜잭션 안에서 실행


//JPA 어플리케이션 개발
//public static void main(String[] args) {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//    EntityManager em = emf.createEntityManager(); // 예를 들어 고객이 들어와서 행위를 하고 나가 이런걸 할 때 고객이 어떤 상품을 장바구니에 담아라던가 이런걸 할때마다
//    // DB 커넥션을 얻어서 쿼리를 날리고 종료되는 그런 한 일관적인 단위를 할 때마다 이 엔티티 메니저라는 애를 꼭 만들어주어야함!!
//    EntityTransaction tx = em.getTransaction();
//    tx.begin();
//    try {
//            Member findMember = em.find(Member.class, 1L);
// JPQL(엔티티 객체) 대상으로 쿼리 -> 장점 방언(oracle, mysql, h2 등 )으로 바꾸거나해도 이 코드를 이 jpql자체 변경 안해도 됨!!!
// SQL(데이터베이스 테이블) 대상으로 쿼리
// JPA 사용하면 엔티티 객체를 중심으로 개발하게 된다.
// 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색한다.
// 모든 DB를 객체로 변환하여 검색 불가능!
// 애플리케이션에 필요한 데이터만 DB에 불러오면 결국 검색 조건이 포함된 SQL 날려야함!
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5) // 5번부터 8개 가져와
//                    .setMaxResults(8)
//                    .getResultList(); // JPA는 테이블 대상으로 절때 코드 짜지 않는다.!
//            for(Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

// 비영속 영속
//try {
//
//        //비영속
//        Member member = new Member();
//        member.setId(101L);
//        member.setName("HelloJPA");
//
//        // 영속
//        System.out.println("===BEFORE===");
//        em.persist(member); // 메니저 안에 있는 영속성 컨텍스트라는 데를 통해서 이 멤버가 관리가 된다! (DB저장 되진 않음)
////            em.detach(member); // 영속성 컨텍스트에서 다시 지움
//        System.out.println("===AFTER===");
//
//        Member findMember = em.find(Member.class, 101L);
//
//        System.out.println("findMember.id = " + findMember.getId());
//        System.out.println("findMember.name = " + findMember.getName());
//        tx.commit();
//        }


//    // 영속
//    // JPA는 엔티티를 조회하거나 하면 무조건 영속성 컨텍스트에 다 올립니다.
//    Member findMember1 = em.find(Member.class, 101L);
//    Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("result = " + (findMember1 == findMember2));
////jpa가 이 영속 엔티티의 동일성을 보장해줍니다. == 비교를 보장해줍니다.


//try {
//        // 영속
//        Member member1 = new Member(150L,"A");
//        Member member2 = new Member(160L,"B");
//
//        // 이 순간 DB에 저장되는게 아니라 그냥 영속성 컨텍스트에 차곡차곡 (엔티티, 쿼리) 쌓이게 됩니다.
//        em.persist(member1);
//        em.persist(member2);
//
//        System.out.println("====================");
//
//        tx.commit();
//        }


//  try {
//          // 영속
//          //jpa는 값을 바꾸면 트랜잭션이 커밋되는 시점에 변경을 반영하는 구나 라고 생각하고 코드를 작성!
//          Member member = em.find(Member.class, 150L);
//        member.setName("ZZZZZ");
//
////            em.persist(member); // list array처럼 값을 변경후 커밋 안하는 것처럼 JPA도 똑같음
//
//        System.out.println("====================");
//
//        tx.commit();


// flush
// try {
//         // 영속
//         Member member = new Member(200L, "member200");
//         em.persist(member);
//
//         //데이터베이스에 반영을 하고 싶거나 쿼리를 미리 보고 싶다면 flush사용하여 강제 호출
//         //flush 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화 한다.
//         em.flush();
//
//         System.out.println("====================");
//
//         tx.commit();
//         }

//    // 영속
//    Member member = em.find(Member.class, 150L);
////            member.setName("AAAAA");
//
////            em.detach(member); //특정 엔티티만 준영속 상태로 전환할 때는 detach 사용
//            em.clear();// 엔티티 매니저 안에 있는 영속성 컨텍스트를 통째로 지워버림!
//
//                    System.out.println("====================");
//
//                    Member member2 = em.find(Member.class, 150L);