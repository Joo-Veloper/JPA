# JPA

## @Enumerated(EnumType.STRING)
@Enumerated의; 기본타입은 ORDINAL입니다.
@Enumerated(EnumType.STRING) 어노테이션은 JPA(Java Persistence API)에서 열거형(enum) 필드를 매핑할 때 사용됩니다. </br>
열거형을 데이터베이스에 저장할 방법을 지정하는데, EnumType.STRING은 열거형의 이름을 문자열로 저장하라는 것을 의미합니다.</br>
반면, EnumType.ORDINAL은 열거형의 순서(0부터 시작)를 정수 값으로 저장하라는 것을 의미합니다.

EnumType.ORDINAL을 사용하는 것이 권장되지 않는 주된 이유는, </br>
열거형 상수의 순서가 변경되거나 중간에 새로운 상수가 추가될 경우 데이터베이스에 저장된 값이 더 이상 정확한 열거형 값을 반영하지 못하기 때문입니다.</br>
예를 들어, 다음과 같은 열거형이 있다고 가정해보면

```
public enum Status {
    NEW, IN_PROGRESS, COMPLETED
}
```
이 열거형을 EnumType.ORDINAL로 저장했다고 가정하면, NEW는 0, IN_PROGRESS는 1, COMPLETED는 2로 데이터베이스에 저장됩니다. </br>
만약 나중에 열거형에 새로운 상태를 추가하고자 하여 IN_PROGRESS와 COMPLETED 사이에 PENDING 상태를 추가한다면, PENDING은 2가 되고, 기존에 COMPLETED였던 상태는 3으로 밀려나게 됩니다.</br>
이렇게 되면 데이터베이스에 저장된 기존의 데이터가 더 이상 정확한 상태를 반영하지 못하게 됩니다.</br>

```
public enum Status {
    NEW, IN_PROGRESS, PENDING, COMPLETED // PENDING 추가
}
```
따라서, 열거형의 순서가 변경되거나 중간에 열거형 상수가 추가되어도 영향을 받지 않도록 EnumType.STRING을 사용하여 열거형의 이름을 문자열로 저장하는 것이 권장됩니다. 
이 방법을 사용하면, 데이터베이스에 저장된 값이 항상 명확하고 안정적으로 열거형 값을 반영하게 됩니다.

## 엔티티 설게시 주의점

Setter 사용 x</br>
관계는 지연로딩 설정</br>


## N + 1 문제
Eager로 N + 1 문제 해결 XXX

X To Many 는 기본이 Lazy여서 그냥 두어도 됨 하지만 그렇지 않고 기본이 Eager인 것들은 Lazy로 다 
바꿔야함!

## AllArgsConstructor
Lombok에서 제공하는 기능이며 클래스의 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해주느 기능입니다.
이 기능을 사용함으로 일일이 생성자 작성하는 번거로움을 줄일수 있습니다.
```
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String address;
    
    public static void main(String[] args) {
        Person person = new Person("John", 30, "123 Main St");
        System.out.println(person.getName());  // 출력: John
    }
}
```

## RequiredArgsConstructor
Lombok 에서 제공하는 기능이며 클래스 내에 final로 선언되거나 @NotNull 어노테이션이 붙은 필드에 대해 생성자를 자동으로 생성해주는 어노테이션입니다.
@RequiredArgsConstructor의 주요 목적은 객체의 필수 필드만을 초기화하는데 필요한 생성자를 최소한의 코드로 제공하는 것입니다. 이는 클래스의 불변성을 유지하면서도 필요한 필드에 대한 초기화를 보장하는
간결한 방법을 제공합니다.

```import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Book {
    private final String title;
    private final String author;
    @NonNull private Integer publicationYear;
    private boolean inPrint;

    public static void main(String[] args) {
        // publicationYear는 @NonNull 어노테이션이 붙어 있으므로 생성자에서 초기화가 필요합니다.
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        System.out.println(book.title + ", " + book.author + ", " + book.publicationYear);
    }
}
```

## JUnit4와 JUnit5

##### JUnit4
```
@Test(expected = IllegalStateException.class)
public void 중복_확인_예약() throws Exception {
    // given
    Member member1 = new Member();
    member1.setName("Park");

    Member member2 = new Member();
    member2.setName("Park");

    // when
    memberService.join(member1);
    memberService.join(member2); // 이 줄에서 IllegalStateException이 발생해야 함

    fail("예외가 발생해야 한다");
}
```
이 코드는 JUnit 4 스타일을 따릅니다. @Test 어노테이션의 expected 속성을 사용해 특정 타입의 예외가 테스트 메서드 실행 중에 발생해야 함을 명시합니다.
예상한 예외가 발생하면 테스트는 성공적으로 통과합니다. fail() 메서드 호출은 실제로는 여기서 필요하지 않습니다. 
왜냐하면, expected 속성으로 이미 예외 발생을 기대하고 있기 때문에, 예외가 발생하지 않아 fail() 호출에 도달하면 JUnit이 테스트 실패로 간주합니다.
##### JUnit5

```
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

```
이 방식은 JUnit 5에서 제공하는 assertThrows 메서드를 사용합니다. assertThrows는 첫 번째 파라미터로 예상되는 예외 타입을 받고,
두 번째 파라미터로는 예외를 발생시킬 수 있는 실행 가능 코드(람다 표현식)를 받습니다. 
예외가 발생하면 테스트가 성공합니다. 만약 예외가 발생하지 않으면 테스트는 실패합니다. 
이 방식은 특정 작업을 수행할 때 정확히 어떤 예외가 발생하는지를 테스트할 때 유용합니다.


두 방식 모두 특정 조건에서 예외가 발생하는지 확인하는 데 사용됩니다. 그러나 assertThrows 방식(JUnit 5)은 예외를 더 세밀하게 처리할 수 있게 해주며, 
특정 부분의 코드에서만 예외 발생을 확인하고자 할 때 더 적합할 수 있습니다. 반면, JUnit 4의 expected 방식은 메서드 전체에서 단 하나의 예외만을 기대할 때 간단하고 직관적입니다.


API만들때 Entity를 절때 반환해서는 안됨
API 스펙이 변환되어버림
member entity에 비밀번호가 있다는 가정하에 Entity를 반환하게 되면 비밀번호가 노출됨!



데이터베이스와 ORM(Object-Relational Mapping)을 사용할 때, 연관된 엔티티를 어떻게 로딩할지 결정하는 것은 매우 중요한 설계 결정 중 하나입니다. 주로 두 가지 로딩 전략이 있습니다: 즉시 로딩(EAGER 로딩)과 지연 로딩(LAZY 로딩). 이 둘 사이의 선택은 애플리케이션의 성능에 큰 영향을 미칠 수 있습니다.

### 즉시 로딩 (EAGER Loading)

즉시 로딩은 연관된 엔티티나 컬렉션을 부모 엔티티를 로드하는 시점에 즉시 모두 로드하는 전략입니다. 예를 들어, `Post` 엔티티와 `Comment` 엔티티가 있고, `Post`가 `Comment`를 즉시 로딩으로 설정하면 `Post`를 조회할 때 연관된 모든 `Comment`도 함께 데이터베이스에서 조회됩니다.

#### 즉시 로딩의 단점
- **과도한 데이터 로드**: 필요하지 않은 경우에도 항상 연관된 엔티티를 로드하기 때문에, 필요 이상의 데이터를 로드하게 되어 성능 문제를 유발할 수 있습니다.
- **N+1 문제**: 한 번의 쿼리로 N개의 엔티티를 로드하고 각 엔티티에 대해 추가 쿼리를 실행하여 연관 엔티티를 로드하는 문제가 발생할 수 있습니다.
- **성능 튜닝의 어려움**: 모든 연관 데이터가 항상 함께 로드되므로, 세밀한 성능 최적화를 수행하기 어렵습니다.

### 지연 로딩 (LAZY Loading)

지연 로딩은 연관된 엔티티나 컬렉션을 실제로 접근하는 시점까지 로드를 지연시키는 전략입니다. 즉, `Post` 엔티티를 로드할 때는 연관된 `Comment`는 로드되지 않다가, 실제로 `Comment`에 접근할 때 데이터베이스에서 로드됩니다.

#### 지연 로딩의 장점
- **성능 최적화**: 필요할 때만 연관된 엔티티를 로드하기 때문에, 불필요한 데이터 로드를 방지하고 성능을 최적화할 수 있습니다.
- **리소스 절약**: 초기 데이터 로드 시 필요하지 않은 데이터를 로드하지 않기 때문에, 메모리 사용량과 데이터베이스 부하를 줄일 수 있습니다.

### 페치 조인 (Fetch Join) 사용하기

성능 최적화가 필요한 경우에는 페치 조인을 사용하는 것이 좋습니다. 페치 조인은 SQL JOIN을 사용하여 연관된 엔티티를 한 번의 쿼리로 함께 로드하는 방법입니다. 이는 필요한 데이터만 선택적으로 로드하면서도, N+1 문제를 피할 수 있는 효과적인 방법입니다.

#### 페치 조인의 장점
- **성능 최적화**: 필요한 데이터만 명시적으로 로드하여 성능을 최적화할 수 있습니다.
- **N+1 문제 해결**: 한 번의 쿼리로 필요한 모든 데이터를 로드할 수 있어, N+1 문제를 방지합니다.

즉, 기본적으로 지연 로딩을 사용하고, 성능 최적화가 필요한 부분에 대해서는 페치 조인을 적절히 활용하는 것이 좋은 전략입니다. 이를 통해 애플리케이션의 성능을 효율적으로 관리하고 최적화할 수 있습니다.