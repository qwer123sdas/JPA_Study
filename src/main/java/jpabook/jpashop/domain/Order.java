package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 매핑을 무엇으로 한 건지 지정, pk는 member_id fk는 member
    private Member member;

    @OneToMany(mappedBy = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간간

    @Enumerated(EnumType.STRING)  // ORDINAL : 숫자로 들어감, 단점은 중간에 enum이 들어가면 꼬임. 그러모르 STRING으로 넣어주기
    private OrderStatus status; // 주문 상태 : ORDER, CANCEL
}
