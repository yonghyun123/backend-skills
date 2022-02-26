package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
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
    @Autowired private ItemRepository itemRepository;
    @Autowired private ItemService itemService;
    @Autowired private EntityManager em;

    @Test
    public void 상품_등록(){
        Item book = new Book();
        book.setName("book");
        book.setPrice(1000);
        book.setStockQuantity(10);


        //when
        itemService.saveItem(book);

        //then
        em.flush();

        Assertions.assertThat(book).isEqualTo(itemService.findOne(book.getId()));
    }
}