package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // == 비즈니스 로직 == //
    // 제고 늘고 줄고를 여기다가 넣음
    // 엔티티 자체에서 해결할 수 있는 로직은 여기다가 비지니스 로직을 넣는 것이 좋다. 보다 응집도 + 객체 지향적임
    // 편의로 setter보단, 변경해야 할 일이 있으면 핵심 비지니스 로직을 바깥에서 하는게 아니라 이 안에서 변경해 주는 것이 가장 객체지향적이다.
    /*
    * stock 증가
    * */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /*
    * stock 감소
    * */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public void change(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
