package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Product;
import java.util.TreeSet;

public class RepositoryProducts implements IRepositoryProducts{
   TreeSet<Product> products;
    
   public RepositoryProducts(){
       products = new TreeSet<>();
   }
}
