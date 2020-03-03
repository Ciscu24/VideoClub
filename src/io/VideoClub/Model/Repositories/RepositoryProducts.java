package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RepositoryProducts implements IRepositoryProducts {

    List<Product> products;

    public RepositoryProducts() {
        products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(String name) {
        boolean result = false;
        if (name != null) {
            Product p = new Product(name) {
            };
            products.add(p);
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeProduct(String name) {
        boolean result = false;
        Iterator<Product> it = products.iterator();
        if (name != null) {
            while (it.hasNext() && !result) {
                result = it.next().getName().toLowerCase().equals(name.toLowerCase());
                if (result) {
                    it.remove();
                }
            }
        }
        return result;
    }

    @Override
    public boolean editProduct(String key, Product newP) {
        boolean result = false;
        if (key != null) {
            for (int i = 0; i < products.size() && !result; i++) {
                if (products.get(i).getKey().equals(key)) {
                    products.set(i, newP);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public Set<Product> listAllProducts() {
        Set<Product> result = new TreeSet<>();
        Iterator<Product> i = products.iterator();

        while (i.hasNext()) {
            result = (Set<Product>) i.next();
        }

        return result;
    }

    @Override
    public Set<Product> listAllProducts(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByName(String name, ProductsTypes type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByStatus(Product.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createProduct(String name, String description, double prize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
