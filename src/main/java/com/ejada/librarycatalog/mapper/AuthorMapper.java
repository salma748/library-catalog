package com.ejada.librarycatalog.mapper;

import com.ejada.librarycatalog.dto.AuthorRequestDto;
import com.ejada.librarycatalog.dto.AuthorResponseDto;
import com.ejada.librarycatalog.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequestDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setEmail(dto.getEmail());
        author.setNationality(dto.getNationality());
        return author;
    }

    public AuthorResponseDto toResponseDto(Author author) {
        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getNationality()
        );
    }

    public void updateEntity(Author author, AuthorRequestDto dto) {
        author.setName(dto.getName());
        author.setEmail(dto.getEmail());
        author.setNationality(dto.getNationality());
    }
}