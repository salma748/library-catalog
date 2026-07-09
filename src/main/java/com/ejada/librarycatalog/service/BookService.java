package com.ejada.librarycatalog.service;

import com.ejada.librarycatalog.dto.BookRequestDto;
import com.ejada.librarycatalog.dto.BookResponseDto;
import com.ejada.librarycatalog.entity.Author;
import com.ejada.librarycatalog.entity.Book;
import com.ejada.librarycatalog.exception.ResourceNotFoundException;
import com.ejada.librarycatalog.mapper.BookMapper;
import com.ejada.librarycatalog.repository.AuthorRepository;
import com.ejada.librarycatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookService(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            BookMapper bookMapper) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    public void createBook(BookRequestDto bookRequestDto) {
        Author author = authorRepository
                .findById(bookRequestDto.getAuthorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: "
                                        + bookRequestDto.getAuthorId()
                        )
                );
        Book book = bookMapper.toEntity(
                bookRequestDto,
                author
        );
        bookRepository.save(book);
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        )
                );
        return bookMapper.toResponseDto(book);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        )
                );
        Author author = authorRepository
                .findById(bookRequestDto.getAuthorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: "
                                        + bookRequestDto.getAuthorId()
                        )
                );
        bookMapper.updateEntity(
                existingBook,
                bookRequestDto,
                author
        );
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toResponseDto(updatedBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id
                        )
                );
        bookRepository.delete(book);
    }

    public List<BookResponseDto> getBooksByAuthorId(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException(
                    "Author not found with id: " + authorId
            );
        }
        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }
}