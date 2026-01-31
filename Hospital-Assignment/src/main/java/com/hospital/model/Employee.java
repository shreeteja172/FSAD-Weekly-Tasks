package com.hospital.model;

import javax.persistence.*;
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation; // e.g., "Developer", "Manager"

    // Standard Getters and Setters
}