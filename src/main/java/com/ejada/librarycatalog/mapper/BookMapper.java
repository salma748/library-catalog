package com.ejada.librarycatalog.mapper;

import com.ejada.librarycatalog.dto.BookRequestDto;
import com.ejada.librarycatalog.dto.BookResponseDto;
import com.ejada.librarycatalog.entity.Author;
import com.ejada.librarycatalog.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public Book toEntity(BookRequestDto dto, Author author) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setAuthor(author);
        return book;
    }

    public BookResponseDto toResponseDto(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                authorMapper.toResponseDto(book.getAuthor())
        );
    }

    public void updateEntity(Book book, BookRequestDto dto, Author author) {
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setAuthor(author);
    }
}