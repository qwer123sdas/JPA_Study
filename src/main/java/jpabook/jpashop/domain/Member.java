package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함했다라는 의미
    private Address address;

    @OneToMany(mappedBy = "member")  // 매핑된 거울일 뿐, 읽기 전용일 뿐, 값을 넣는다고 fk쪽이 바뀌지 않음. 즉 order테이블에 있는 member를 가리킴
    private List<Order> orders = new ArrayList<>();
}
