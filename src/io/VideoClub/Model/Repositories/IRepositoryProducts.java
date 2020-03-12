package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRepositoryProducts {
    Set<Product> listAllProducts();
    Set<Product> listAllProducts(Comparator c);
    List<Product> listAllProductsNoDuplicates();
    Set<Product> listAllByType(ProductsTypes type);
    Set<Product> listAllByName(String name);
    Set<Product> listAllByName(String name,ProductsTypes type);
    Set<Product> listAllByStatus(Product.Status status);
    
    List<Product> listAllDifferentProducts();
    List<Product> listAllDifferentMovies();
    List<Product> listAllDifferentGames();
    
    Map<Product,Integer> listAllAmountOfProducts(String name); 
    Map<Product,Integer> listAllAmountOfProducts(ProductsTypes type,String name);
    
    boolean createProduct(String name, String description,double prize);
    boolean createMovie(ProductsTypes type,String name, String description, MovieCategory cat,int minAge);
    boolean createGame(ProductsTypes type, String name, String description, GameCategory cat,int minAge);
    
    boolean addProduct(String name);
    boolean removeProduct(String name);
    
    boolean editProduct(String key, Product newP);
    
    Product isAvailableProduct(String name);  //get product if yes
    boolean reserveProduct(Product prod,IClient client);
    boolean returnedProduct(Product prod, IClient client);
}
