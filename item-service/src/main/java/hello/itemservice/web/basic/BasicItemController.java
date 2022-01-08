package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
/**
 * RequiredArgsConstructor 해당 어노테이션을 통해
 * 밑에 final 변수에 repository 객체의 의존성 주입을 가능하도록 함
 *     @Autowired
 *     public BasicItemController(ItemRepository itemRepository) {
 *         this.itemRepository = itemRepository;
 *     }
 *
 *     위의 소스 역할을 하게 됨
 *
 */
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA", 10000, 10));
        itemRepository.save(new Item("ItemB", 20000, 20));
    }
}
