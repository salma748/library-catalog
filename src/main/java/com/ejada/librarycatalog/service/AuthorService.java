package com.ejada.librarycatalog.service;

import com.ejada.librarycatalog.dto.AuthorRequestDto;
import com.ejada.librarycatalog.dto.AuthorResponseDto;
import com.ejada.librarycatalog.entity.Author;
import com.ejada.librarycatalog.exception.ResourceNotFoundException;
import com.ejada.librarycatalog.mapper.AuthorMapper;
import com.ejada.librarycatalog.repository.AuthorRepository;
import com.ejada.librarycatalog.exception.BusinessConflictException;
import com.ejada.librarycatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            AuthorMapper authorMapper) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    public void createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = authorMapper.toEntity(authorRequestDto);
        authorRepository.save(author);
    }

    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDto)
                .toList();
    }

    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + id
                        )
                );

        return authorMapper.toResponseDto(author);
    }

    public AuthorResponseDto updateAuthor(
            Long id,
            AuthorRequestDto authorRequestDto) {

        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + id
                        )
                );

        authorMapper.updateEntity(
                existingAuthor,
                authorRequestDto
        );

        Author updatedAuthor = authorRepository.save(existingAuthor);

        return authorMapper.toResponseDto(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id: " + id
                        )
                );
        if (bookRepository.existsByAuthorId(id)) {
            throw new BusinessConflictException(
                    "Cannot delete author with id "
                            + id
                            + " because the author has associated books"
            );
        }
        authorRepository.delete(author);
    }
}