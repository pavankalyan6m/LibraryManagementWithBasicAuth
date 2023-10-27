package com.practiceproject1.Controller;

import com.practiceproject1.Models.Book;
import com.practiceproject1.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library-api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/user/getAllBooks")
    @PreAuthorize("hasRole('USER')")
    public List<Book> getBooks(){
        return bookService.getAllBooks()
                .stream().collect(Collectors.toList());
    }

    @GetMapping("/user/getBook/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<Book> getABook(@PathVariable int id)
    {
       return bookService.getBookById(id).stream().findFirst();
    }


    @PostMapping("/admin/addNewBook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addABook(@RequestBody Book book)
    {
        bookService.addBook(book);
        return ResponseEntity.ok("Yay!!..A new Book Added Successfully.");
    }


    @PutMapping("/admin/updateBookDetails/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateBookDetailsById(@PathVariable int id, @RequestBody Book updatedBook)
    {
         bookService.updateBook(id,updatedBook);
        return ResponseEntity.ok("Yay!!..the desired book details updated Successfully.");

    }

    @DeleteMapping("/deleteBookById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBookById(@PathVariable int id)
    {
        bookService.deleteBook(id);
        return ResponseEntity.ok("OOps!..Book details deleted Successfully.");

    }
}
