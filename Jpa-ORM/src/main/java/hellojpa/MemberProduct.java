package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {
    @Id
    @GeneratedValue
    private Long id;
    //MEMBER (N:1)
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //PRODUCT (N:1)
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;
    private LocalDateTime orderDateTime;
}
