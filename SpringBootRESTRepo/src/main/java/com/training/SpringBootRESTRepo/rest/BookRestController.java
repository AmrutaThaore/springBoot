package com.training.SpringBootRESTRepo.rest;
import com.training.SpringBootRESTRepo.entity.Book;
import com.training.SpringBootRESTRepo.service.BookService;
import com.training.SpringBootRESTRepo.utility.AppConstants;
import com.training.SpringBootRESTRepo.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    public BookRestController() {
        System.out.println("Book Rest Controller default constructor");
    }
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String author){
        if(author==null)
            return bookService.getAllBooks();
        return bookService.getBooksByAuthor(author);
    }
    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        System.out.println("Book "+book);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.addNewBook(book) );
            return ResponseEntity.ok(map);
        }
        catch (RuntimeException e){
            map.put(AppConstants.STATUS, Status.FAILURE);
            map.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }
    //    @GetMapping("/{id}")
//    public Book getBookById(@PathVariable int id){
//        return bookService.getBookById(id);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(AppConstants.STATUS, Status.SUCCESS);
            map.put("book",bookService.getBookById(id) );
            return ResponseEntity.ok(map);
        }
        catch (RuntimeException e){
            map.put(AppConstants.STATUS, Status.FAILURE);
            map.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }
    }


}