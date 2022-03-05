package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createForm(BookForm bookForm) {

//        book.setStockQuantity(bookForm.getStockQuantity());
//        book.setName(bookForm.getName());
//        book.setPrice(bookForm.getPrice());
//        book.setAuthor(bookForm.getAuthor());
//        book.setIsbn(bookForm.getIsbn());
        Book book = Book.createBook(bookForm.getName(),
                bookForm.getPrice(),
                bookForm.getStockQuantity(),
                bookForm.getAuthor(),
                bookForm.getIsbn()
        );


        itemService.saveItem(book);

        return "redirect:/";
    }
}
