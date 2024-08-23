package hellojpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member{
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVOIRTE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @OrderColumn(name = "address_history_order")
//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Address getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}



////@TableGenerator(
////        name = "MEMBER_SEQ_GENERATOR",
////        table = "MY_SEQUENCES",
////        pkColumnName = "MEMBER_SEO", allocationSize = 1)
////@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq") //Sequence전략
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEBER_SEQ",
//        initialValue = 1, allocationSize = 50)
//
////@Table (uniqueConstraints = ) //유니크 제약 조건 걸때 사용가능
////@Table(name = "MBR") // 예를들어 테이블을 다른 이름을 맵핑하고 싶어 dba가 멤버라고 쓰면 안되요! 에를 들어 mbr이라고 쓰야하 해요 라고 하면 @Table(name = "MBR")이라고 쓰면 됨
////@Table(name = "USER") //멤버 이름이 다를때 : 데이터 베이스에 멤버가 아니라 유저 라고
//// 되어있다면 @Table(name = "USER") 해주면 쿼리가 나갈때 user 테이블에 인서트 하고 나간다.!
//@Entity //jpa가 관리하는 객체!
//public class Member {
//    @Id // 내가 직접 Id 세팅해주는 것!!
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//    generator = "MEMBER_SEQ_GENERATOR")
//    // @GeneratedValue(strategy = GenerationType.IDENTITY)
//    // 기본키 매핑 어노테이션 사용 가능한것 1. @Id 2.@GeneratedValue
//    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")//Sequence전략
//    // 기본값 auto = db에 맞춰서 자동 생성!
//    // IDENTITY 기본키 생성을 데이터베이스에 위임한다.!
//    // SEQUENCE
//    // TABLE
//    private Long id;
//    @Column(name = "name", nullable = false)
//    private String username;
//    public Member() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//}
//
//
//// mapping / Column 설명
////@Id
////private Long id;  //pk 매핑
////    @Column(name = "name", nullable = false) // 데이터 베이스 컬럼명 name
////    // insertable 데이터베이스에서 insert를 할것이냐 안할 것이냐
////    // updatable = false로 해두시면 업데이트 안됨! 물론 DB에서 강제로 하면 가능하지만 JPA에서는 불가능!
////    // nullable = false / unique 제약조건은 잘 사용하지 않음!!
////    // length = 10
////    // columnDefinition = "varchar(100) default 'EMPTY'"  -> 이 문구가 DDL로 다들어가게 된다.!!!
////
//////    @Column()
//////    private BigDecimal age;// 아주 큰 숫자나 소숫점 쓸때 사용!!
////
////    private String username;
////    private Integer age;
////    @Enumerated(EnumType.STRING)//EnumType 기본이 ORDINAL이다. ORDINAL = enum의 순서를 DB에 저장하고 STRING은 enum의 이름을 그대로 저장
////    // 운영 상에서 EnumType 쓰면 안됨!!!!!!!!!!!!!! 필수로 스트링 기본값이 ORDINAL 이길 때문에 String 사용
////    private RoleType roleType;
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date createdDate; // 생성
////    @Temporal(TemporalType.TIMESTAMP)
////    private Date lastModifiedDate; // 수정
////
////    private LocalDate testLocalDate1;
////    private LocalDateTime testLocalDateTime2;
////    @Lob
////    private String description;
////    @Transient// DB랑 신경쓰고 싶지 않을떄 Transient 사용
////    private int temp;
////    //    @Id //db에게 pk가 뭔지 알려 줘야 함!!
//////    private Long id;
////////    @Column을 매핑해주면 됩니다. @Column(name = "username")
//////    @Column(unique = true, length = 10)
//////    private String name;
////    public Member() {
////    }
////
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
////
////    public RoleType getRoleType() {
////        return roleType;
////    }
////
////    public void setRoleType(RoleType roleType) {
////        this.roleType = roleType;
////    }
////
////    public Date getCreatedDate() {
////        return createdDate;
////    }
////
////    public void setCreatedDate(Date createdDate) {
////        this.createdDate = createdDate;
////    }
////
////    public Date getLastModifiedDate() {
////        return lastModifiedDate;
////    }
////
////    public void setLastModifiedDate(Date lastModifiedDate) {
////        this.lastModifiedDate = lastModifiedDate;
////    }
////
////    public String getDescription() {
////        return description;
////    }
////
////    public void setDescription(String description) {
////        this.description = description;
////    }
////
////    public int getTemp() {
////        return temp;
////    }
////
////    public void setTemp(int temp) {
////        this.temp = temp;
////    }
////
////    public String getUsername() {
////        return username;
////    }
////
////    public void setUsername(String username) {
////        this.username = username;
////    }
////
////    public Integer getAge() {
////        return age;
////    }
////
////    public void setAge(Integer age) {
////        this.age = age;
////    }