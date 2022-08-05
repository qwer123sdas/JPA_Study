package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery{

    @Id @GeneratedValue
    @Column(name ="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  // ORDINAL : 숫자로 들어감, 단점은 중간에 enum이 들어가면 꼬임. 그러모르 STRING으로 넣어주기
    private DeliveryStatus status; // READY, COMP
}
