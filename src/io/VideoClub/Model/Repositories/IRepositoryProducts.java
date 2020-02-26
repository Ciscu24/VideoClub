package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Product;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public interface IRepositoryProducts {
    Set<Product> listAllProducts();
    Set<Product> listAllProducts(Comparator c);
    Set<Product> listAllByType(ProductsTypes type);
    Set<Product> listAllByName(String name);
    Set<Product> listAllByName(String name,ProductsTypes type);
    Set<Product> listAllByStatus(Product.Status status);
    
    Map<Product,Integer> listAllAmountOfProducts(String name); 
    Map<Product,Integer> listAllAmountOfProducts(ProductsTypes type,String name);
    
    boolean createProduct(String name, String description,double prize);
    
    boolean addProduct(String name);
    boolean removeProduct(String name);
    
    boolean editProduct(String key, Product newP);
}
