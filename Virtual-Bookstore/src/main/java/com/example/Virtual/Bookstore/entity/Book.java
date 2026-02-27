package com.example.Virtual.Bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String genre;

    private String category;

    @Column(length = 2000)
    private String description;

    private double price;

    private double rating; // average rating

    private int stock; // number of available copies

    @Column(nullable = false)
    private boolean available = true; // ✅ added default value
}