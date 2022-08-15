package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 책등록() throws Exception{
        //given

        Book book = new Book();
        Book book1 = book.createBook("111", 111, 111, "asd", "12312");

        /*book.setName("111");
        book.setPrice(111);
        book.setStockQuantity(111);
        book.setAuthor("asd");
        book.setIsbn("12342");*/

        //when
        Long id = itemService.saveItem(book1);

        //then
        itemRepository.findOne(id);

        assertEquals("같은가?", "111", book1.getName());
    }
}