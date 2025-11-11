package com.biblio.backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String nationality;

    private Integer birthYear;
    private String biography;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    // --------- GETTERS ---------
    public String getName() {
        return name;
    }

    public String getNationality(){
        return nationality;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getBiography(){
        return biography;
    }


    // --------- SETTERS ---------
    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setBiography(String biography){
        this.biography = biography;
    }





}
