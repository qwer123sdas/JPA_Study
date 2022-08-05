package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),  // 중간 테이블(category_item)에 있는 카테고리 아이디
            inverseJoinColumns = @JoinColumn(name = "item_id")) // category_item 테이블에서 item쪽으로 가도록
    private List<Item> items = new ArrayList<>();

    // 다른 entity를 매핑해주는 것처럼, 자기 자신을 부모-자식으로 매핑
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


}
