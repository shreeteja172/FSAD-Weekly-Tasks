package com.hospital.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hospital.model.*; // Import all models

public class MainApp {
    public static void main(String[] args) {
        
        // 1. Create Session Factory and Register ALL classes
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Appointment.class)
                .addAnnotatedClass(Patient.class)
                .buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        try {
            session.beginTransaction();

            // --- Exercise 7: Delete Inactive Customers ---
            String hqlDelete = "DELETE FROM Customer WHERE accountStatus = :status";
            int deleted = session.createQuery(hqlDelete).setParameter("status", "inactive").executeUpdate();
            System.out.println("Deleted " + deleted + " inactive records.");

            // --- Exercise 6: Fetch Book by ISBN ---
            String targetIsbn = "978-0134685991"; 
            Book retrievedBook = session.get(Book.class, targetIsbn);
            if (retrievedBook != null) {
                System.out.println("\nBook Found: " + retrievedBook.getTitle());
            }

            // --- Exercise 8: Sorting Appointments by Fee ---
            System.out.println("\n--- Appointments (Highest Fee First) ---");
            session.createQuery("FROM Appointment ORDER BY consultationFee DESC", Appointment.class)
                   .list().forEach(System.out::println);

            // --- Exercise 9: Pagination (First 3 Records) ---
            System.out.println("\n--- Pagination: Showing First 3 Appointments ---");
            session.createQuery("FROM Appointment", Appointment.class)
                   .setFirstResult(0)
                   .setMaxResults(3)
                   .list().forEach(System.out::println);

            // --- Exercise 10: Aggregation (Total Patient Count) ---
            Long totalPatients = session.createQuery("SELECT COUNT(p) FROM Patient p", Long.class)
                                        .uniqueResult();
            System.out.println("\nTotal Patients in Hospital: " + totalPatients);

            session.getTransaction().commit();
            System.out.println("\nAll exercises executed successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}