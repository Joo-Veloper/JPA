package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    // 1:1
    // @OneToOne(mappedBy = "locker")
    // private Locker locker; // 읽기 전용이됨
}
