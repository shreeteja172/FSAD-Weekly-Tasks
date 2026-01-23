package cm.example.main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.entity.Product;
import cm.example.util.HibernateUtil;

public class ProductMain {

    public static void main(String[] args) {

        // Using try-with-resources ensures the session closes automatically
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            Transaction tx = session.beginTransaction();

            // 1. Insert Products 
            // session.persist is preferred over session.save in modern Hibernate
            session.persist(new Product("Laptop", "Electronics", 60000, 10));
            session.persist(new Product("Mouse", "Electronics", 500, 50));
            session.persist(new Product("Keyboard", "Electronics", 1500, 20));
            session.persist(new Product("Chair", "Furniture", 3500, 15));
            session.persist(new Product("Table", "Furniture", 8000, 5));

            tx.commit();
            System.out.println("Data saved successfully!");

            // 2. SORT BY PRICE (ASC)
            System.out.println("\n--- Price Ascending ---");
            session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                   .getResultList()
                   .forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

            // 3. PAGINATION (First 3 Products)
            System.out.println("\n--- First 3 Products ---");
            session.createQuery("FROM Product", Product.class)
                   .setFirstResult(0)
                   .setMaxResults(3)
                   .getResultList()
                   .forEach(p -> System.out.println(p.getName()));

            // 4. AGGREGATE FUNCTIONS (Min & Max)
            Object[] minMax = session.createQuery(
                    "SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class)
                    .uniqueResult();
            System.out.println("\nMin Price: " + minMax[0] + " | Max Price: " + minMax[1]);

            // 5. GROUP BY DESCRIPTION
            System.out.println("\n--- Group By Description ---");
            List<Object[]> groupList = session.createQuery(
                    "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description", 
                    Object[].class)
                    .getResultList();
            for (Object[] row : groupList) {
                System.out.println(row[0] + " -> " + row[1]);
            }

            // 6. WHERE + LIKE (Names containing 'top')
            System.out.println("\n--- Names containing 'top' ---");
            session.createQuery("FROM Product p WHERE p.name LIKE :pattern", Product.class)
                   .setParameter("pattern", "%top%")
                   .getResultList()
                   .forEach(p -> System.out.println(p.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\nSUCCESS");
    }
}