package com.ejada.librarycatalog.service;

import com.ejada.librarycatalog.entity.Author;
import com.ejada.librarycatalog.entity.Book;
import com.ejada.librarycatalog.repository.AuthorRepository;
import com.ejada.librarycatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void createBook(Book book) {
        Long authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            return;
        }
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            return null;
        }
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        if (updatedBook.getAuthor() != null) {
            Long authorId = updatedBook.getAuthor().getId();
            Author author = authorRepository.findById(authorId).orElse(null);
            if (author != null) {
                existingBook.setAuthor(author);
            }
        }
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}