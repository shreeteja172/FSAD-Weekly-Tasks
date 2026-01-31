package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "books") // This matches your database table name
public class  Book {
    
    @Id
    @Column(name = "isbn")
    private String isbn; // Primary Key
    
    private String title;
    private String author;
    private double price;

    // Default Constructor required by Hibernate
    public Book() {}

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Book [ISBN=" + isbn + ", Title=" + title + ", Author=" + author + ", Price=" + price + "]";
    }
}