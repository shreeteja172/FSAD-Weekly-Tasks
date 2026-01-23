package com.klhu.service;
import com.klhu.entity.Product;
import java.util.List;
public interface ProductService {
List<Product> getAllProducts();
Product getProductById(Long id);
void ProductsaveProduct(Product product);
Product updateProduct(Long id, Product product);
void deleteProduct(Long id);
Object saveProduct(Product product);
}
