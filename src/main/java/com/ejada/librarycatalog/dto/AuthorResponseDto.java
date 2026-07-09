package com.ejada.librarycatalog.dto;

public class AuthorResponseDto {

    private Long id;
    private String name;
    private String email;
    private String nationality;

    public AuthorResponseDto() {
    }

    public AuthorResponseDto(Long id, String name, String email, String nationality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}