package com.training.SpringBootRESTRepo.service;
import com.training.SpringBootRESTRepo.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> bookList;
    public BookService() {
        System.out.println("Book service default constructor");
        bookList = new ArrayList<>();
        bookList.add(
                new Book(1, "Core Java", "Hotsmann","Learn java fundamentals", 130.0));
        bookList.add(
                new Book(2,"HTML", "Kelly","Learn html for UI", 230.0));
        bookList.add(
                new Book( 3, "python", "ryan","Learn python fundamentals", 130.0));
        bookList.add(
                new Book( 4, "css", "kelly","Learn css for designing webpage", 130.0));
    }
    public long getTotalBookCount(){
        return bookList.size();
    }
    public List<Book> getAllBooks(){
        return bookList;
    }
    public Book addNewBook(Book book){
        for (Book ob : bookList){
            if(ob.getBookid() == book.getBookid())
                throw new RuntimeException("Book with id "+book.getBookid()+" already exists");
        }
        Book lastBook = bookList.get(bookList.size()-1);
        int id = lastBook.getBookid()+ 1;
        book.setBookid(id);
        bookList.add(book);
        return book;
    }
    public Book updateBook(Book book){
        for (int i=0;i<bookList.size();i++){
            if(bookList.get(i).getBookid() == book.getBookid()) {
                bookList.set(i, book);
                return book;
            }
        }
        throw new RuntimeException("Book with id "+book.getBookid()+" does not exist");
    }
    public boolean deleteBook(int id){
        for (int i=0;i<bookList.size();i++){
            if(bookList.get(i).getBookid() == id) {
                bookList.remove(i);
                return true;
            }
        }
        throw new RuntimeException("Book with id "+id+" does not exist");
    }
    public List<Book> getBooksByAuthor(String author){
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book ob : bookList){
            if(ob.getAuthor().equalsIgnoreCase (author))
                booksByAuthor.add(ob);
        }
        return booksByAuthor;
    }
    public Book getBookById(int id){
    for (Book ob : bookList) {
        if (ob.getBookid() == id)
            return ob;
    }
 throw new RuntimeException("Book with id "+id+" does not exists");
}

    public List<Book> getBooksByAthor(String author) {
        return null;
    }
}