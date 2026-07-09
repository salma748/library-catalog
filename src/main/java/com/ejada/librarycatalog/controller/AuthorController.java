package com.ejada.librarycatalog.controller;

import com.ejada.librarycatalog.dto.AuthorRequestDto;
import com.ejada.librarycatalog.dto.AuthorResponseDto;
import com.ejada.librarycatalog.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        authorService.createAuthor(authorRequestDto);
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequestDto authorRequestDto) {

        return authorService.updateAuthor(id, authorRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}