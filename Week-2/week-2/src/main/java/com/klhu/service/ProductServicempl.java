package com.klhu.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klhu.entity.Product;
import com.klhu.repository.ProductRepository;
@Service
public class ProductServicempl implements ProductService {
private final ProductRepository productRepository;
// Constructor-based Dependency Injection
public ProductServicempl(ProductRepository productRepository) {
this.productRepository = productRepository;
}
@Override
public List<Product> getAllProducts() {
return productRepository.findAll();
}
@Override
public Product getProductById(Long id) {
return productRepository.findById(id)
.orElseThrow(() -> new RuntimeException("Product not found"));
}
@Override
public Product saveProduct(Product product) {
return productRepository.save(product);
}
@Override
public Product updateProduct(Long id, Product productDetails) {
Product existingProduct = getProductById(id);
existingProduct.setName(productDetails.getName());
existingProduct.setDescription(productDetails.getDescription());
existingProduct.setPrice(productDetails.getPrice());
existingProduct.setQuantity(productDetails.getQuantity());
return productRepository.save(existingProduct);
}
@Override
public void deleteProduct(Long id) {
productRepository.deleteById(id);
}
}