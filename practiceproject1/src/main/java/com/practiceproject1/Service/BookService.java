package com.practiceproject1.Service;

import com.practiceproject1.Models.Book;
import com.practiceproject1.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream().collect(Collectors.toList());
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id).stream().findFirst();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(int id, Book updatedBook) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(existingBook -> {
            existingBook.setId(updatedBook.getId());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPublication_year(updatedBook.getPublication_year());

            //saving after all the details are updated
            bookRepository.save(existingBook);
        });
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
