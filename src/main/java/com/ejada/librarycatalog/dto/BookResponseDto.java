package com.ejada.librarycatalog.dto;

public class BookResponseDto {

    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private AuthorResponseDto author;

    public BookResponseDto() {
    }

    public BookResponseDto(
            Long id,
            String title,
            String isbn,
            Integer publicationYear,
            AuthorResponseDto author) {

        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public AuthorResponseDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorResponseDto author) {
        this.author = author;
    }
}