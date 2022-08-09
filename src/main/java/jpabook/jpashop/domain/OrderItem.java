package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // wnanstnfid

    // protected로 막아 놓은 이유 : OrderItem 인스터스는 createOrderItem()메소드를 통해 값은 초기화 한다.
    // 하지만 누군가가 생성자 만들어서 set을 통해 초기화 하는 경우, 일관성이 없어지기 때문에 유지보수하기 어렵다.
    // 그러므로 protect를 통해 다른 클래스에서 해당 생성자를 호출하지 못하게 해야 한다.
   /* protected OrderItem(){

    }*/
    // 하지만 이를 도와주는 lombok이 있다. 바로 @NoArgsConstructor(access = AccessLevel.PROTECTED)

    // == 생성 메서드 == //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //
        return orderItem;
    }

    // == 비지니스 로직 == //
    public void cancel() {
        getItem().addStock(count); // 재고 수량을 원복
    }

    // == 조회 로직 == //
    /*
    *  주문 상품 전체 조회
    * */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
