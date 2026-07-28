package com.varun.SpringBootDemoJpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    private int bookId;
    private String name;
}
