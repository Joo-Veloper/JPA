# DATA_JPA


<div>

#### TIP(Leaving a log of query parameters)

```
spring boot version 3.0 이전 버전
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.7'
spring boot version 3.0
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0
```

# Lombok

롬복은 자바 언어를 위한 코드 생성 라이브러리로, 반복적이고 지루한 작업을 줄여주는 기능을 제공합니다. 이는 주로 자바의 getter, setter, equals, hashCode, toString 등의 메서드를 자동으로 생성해줌으로써 개발자가 이런 반복 작업을 직접 구현하지 않아도 되게끔 도와줍니다.


@Setter: 이 어노테이션은 해당 클래스의 필드에 대한 Setter 메서드를 생성합니다. 하지만 실무에서는 보통 Setter를 가급적 사용하지 않는 것이 좋습니다. 왜냐하면 Setter를 통해 객체의 상태를 변경할 수 있기 때문에 객체의 불변성을 보장하기 어렵고, 불변 객체를 유지하는 것이 프로그램의 안정성을 높일 수 있기 때문입니다.</br>

@NoArgsConstructor(access = AccessLevel.PROTECTED): 이 어노테이션은 해당 클래스의 매개변수 없는 생성자를 protected 접근 제어자로 생성합니다. 주로 JPA(Java Persistence API)에서 사용되며, JPA 스펙상 기본 생성자가 필요하지만 외부에서 직접 접근하지 않기를 원할 때 사용됩니다.</br>

@ToString: 이 어노테이션은 해당 클래스의 toString 메서드를 생성합니다. 그러나 연관된 객체의 toString 결과를 포함시키지 않고, 단순히 내부 필드만을 출력하도록 합니다. 이렇게 함으로써 객체를 문자열로 표현할 때 불필요한 정보를 제외할 수 있습니다.</br>

# Query Methods
쿼리 메소드(Query Methods)는 Spring Data JPA에서 제공하는 강력한 기능 중 하나로, 메소드 이름을 통해 쿼리를 생성하거나 직접 쿼리를 정의할 수 있습니다. 이를 통해 쿼리를 작성할 때의 번거로움을 줄이고, 더 간단하고 읽기 쉬운 코드를 작성할 수 있습니다. 

#### 메소드 이름으로 쿼리 생성
Spring Data JPA는 메소드의 이름을 분석하여 자동으로 쿼리를 생성할 수 있습니다. 메소드 이름에는 find, read, query, get 등의 접두사가 있어야 하며, 이후에는 엔티티의 속성 이름이 옵니다. 예를 들어, findByFirstName(String firstName)와 같은 메소드는 firstName 속성을 기반으로 해당 값을 찾는 쿼리를 생성합니다.</br>
#### 메소드 이름으로 JPA NamedQuery 호출
NamedQuery는 엔티티 클래스에 정의된 이름이 있는 쿼리입니다. Spring Data JPA는 메소드 이름을 통해 이러한 NamedQuery를 호출할 수 있습니다. 메소드 이름에는 NamedQuery의 이름이 사용되며, 필요한 경우 메소드의 파라미터를 추가하여 NamedQuery의 파라미터를 전달할 수 있습니다.</br>
#### @Query 어노테이션을 사용해서 리파지토리 인터페이스에 쿼리 직접 정의
@Query 어노테이션을 사용하면 개발자가 직접 JPQL(Java Persistence Query Language)이나 네이티브 쿼리를 작성할 수 있습니다. 이를 통해 메소드 이름을 통한 자동 생성이나 NamedQuery를 사용하는 것보다 더 복잡한 쿼리를 정의할 수 있습니다. @Query 어노테이션을 사용할 때는 JPQL이나 네이티브 SQL을 정확히 작성하고, 필요한 경우 파라미터를 바인딩해야 합니다.</br>


# JPA Named Query

Spring에서 `@NamedQuery`는 JPA(Java Persistence API)를 사용하여 엔티티에 대한 정적인 쿼리를 정의하는 데 사용됩니다. </br>
이를 통해 쿼리를 코드에서 분리하여 유지 보수 및 관리를 쉽게 할 수 있습니다.</br>


`@NamedQuery`를 사용하면 엔티티 클래스에 직접 SQL 또는 JPQL(Java Persistence Query Language) 쿼리를 정의할 수 있습니다. 그러면 해당 쿼리를 호출하는 데 필요한 메서드를 생성할 수 있습니다.</br>
#### @NamedQuery`를 사용하여 JPQL 쿼리를 정의하는 방법

```
@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
```
정의된 JPQL 쿼리는 "Employee" 엔티티에서 lastName 속성이 특정 값과 일치하는 모든 엔티티를 검색합니다. 그런 다음 이 쿼리를 호출하여 특정한 작업을 수행할 수 있습니다.


# Parameter Binding

위치 기반과 이름 기반 파라미터 바인딩은 두 가지 주요한 파라미터 바인딩 방식입니다. 이들 각각은 쿼리에서 파라미터를 바인딩하는 방법을 나타냅니다.

- **위치 기반 파라미터 바인딩**: 위치 기반 파라미터 바인딩은 파라미터를 `?` 기호와 인덱스를 사용하여 쿼리에 바인딩합니다. </br>
이 경우에는 위치(index)에 따라 파라미터가 쿼리에 바인딩됩니다. 즉, 첫 번째 `?0`, 두 번째 `?1`, 이런 식으로 인덱스를 사용하여 쿼리에 바인딩됩니다.</br>

- **이름 기반 파라미터 바인딩**: 이름 기반 파라미터 바인딩은 쿼리에 `:` 기호와 파라미터 이름을 사용하여 파라미터를 바인딩합니다.</br>
이 경우에는 파라미터 이름을 사용하여 쿼리에 값을 제공합니다. 따라서 쿼리에서는 파라미터의 의미를 명시적으로 알 수 있으며, 순서를 고려하지 않아도 됩니다.</br>

위치 기반 파라미터 바인딩은 순서를 실수할 가능성이 있고, 코드의 가독성과 유지 보수성이 떨어질 수 있습니다.</br>
하지만 이름 기반 파라미터 바인딩은 파라미터의 의미를 명확하게 전달하며, 쿼리를 이해하기 쉽게 만들어 줍니다.</br>
그래서 보통 이름 기반 파라미터 바인딩이 코드의 가독성과 유지 보수성을 향상시키는 데 도움이 됩니다. </br>
Spring Data JPA에서는 `@Param` 어노테이션을 사용하여 이름 기반 파라미터 바인딩을 지원하고 있습니다.</br>

</div>


