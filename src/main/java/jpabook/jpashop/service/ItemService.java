package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // 메소드에 가까운게 더 우선권을 가짐
    public Long saveItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto){
        Item findItem = itemRepository.findOne(itemId);
        // findItem은 영속상태임
        // 준영속성인 param을 영속성인 곳에 세팅함
       /* findItem.setName(itemDto.getName());
        findItem.setPrice(itemDto.getPrice());
        findItem.setStockQuantity(itemDto.getStockQuantity());
        // set으로 깔지 말 것.*/

        // 이렇게 바꾸기
        findItem.change(itemDto.getName(), itemDto.getPrice(), itemDto.getStockQuantity());

        // 그리고 트랜잭션라인이 끝나면 commit이 되면서 jpa는 flush() = 변경된 내용 찾음
        // 그리고 바뀐 값을 update자동으로 함. 이것이 변경감지 기능을 사용 하는 것

        // 레파지토리를 호출안해도 됨.

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
