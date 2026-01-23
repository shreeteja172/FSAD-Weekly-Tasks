package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    private int id;
    private String name;
    private String gender;
    private Certification certification;

    // Perfect Practice: Use Constructor Injection
    @Autowired
    public Student(Certification certification) {
        this.id = 101;
        this.name = "Nirmala";
        this.gender = "Female";
        this.certification = certification;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Certification getCertification() { return certification; }
    public void setCertification(Certification certification) { this.certification = certification; }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender + 
               ", certification=" + certification + "]";
    }
}