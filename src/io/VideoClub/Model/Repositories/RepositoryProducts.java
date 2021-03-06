package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Film;
import io.VideoClub.Model.Game;
import io.VideoClub.Model.Other;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Product.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RepositoryProducts implements IRepositoryProducts {

    List<Product> products;
    RepositoryItems items;

    public RepositoryProducts() {
        products = new ArrayList<>();
        items = new RepositoryItems();
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
    public boolean createProduct(String name, String description, double prize) {
        boolean result = false;
        if (name.equals("")) {
            name = "No named product";
        }
        if (description.equals("")) {
            description = "No description provided";
        }
        Product p = new Product(name, description, prize, ProductsTypes.Otros);
        products.add(p);
        result = true;
        return result;
    }

    @Override
    public boolean createMovie(ProductsTypes type, String name, String description, MovieCategory cat, int minAge) {
        boolean result = false;
        if (type.equals(ProductsTypes.Peliculas)) {
            Film newFilm = new Film(cat, minAge, name, description, 0);
            result = true;
        }
        return result;
    }

    @Override
    public boolean createGame(ProductsTypes type, String name, String description, GameCategory cat, int minAge) {
        boolean result = false;
        if (type.equals(ProductsTypes.Peliculas)) {
            Game newGame = new Game(cat, minAge, name, description, 0);
            result = true;
        }
        return result;
    }

    //CREADO ADDMOVIE, ADDGAME Y ADDOTHER A PARTE PORQUE LOS DOS DE ARRIBA NO SIRVEN, preguntar en clase la duda
    public boolean addMovie(ProductsTypes type, String name, String description, MovieCategory cat, int minAge, double prize) {
        boolean result = false;
        if (name == "") {
            name = "No name provided";
        }
        if (description == "") {
            description = "No description provided";
        }
        if (type.equals(ProductsTypes.Peliculas)) {
            Film newFilm = new Film(cat, minAge, name, description, prize);
            products.add(newFilm);
            result = true;
            items.addItem(newFilm);
        }

        return result;
    }

    public boolean addGame(ProductsTypes type, String name, String description, GameCategory cat, int minAge, double prize) {
        boolean result = false;
        if (name == "") {
            name = "No name provided";
        }
        if (description == "") {
            description = "No description provided";
        }
        if (type.equals(ProductsTypes.Juegos)) {
            Game newGame = new Game(cat, minAge, name, description, prize);
            products.add(newGame);
            result = true;
            items.addItem(newGame);
        }
        return result;
    }

    public boolean addOther(ProductsTypes type, String name, String description, double prize) {
        boolean result = false;
        if (name == "") {
            name = "No name provided";
        }
        if (description == "") {
            description = "No description provided";
        }
        if (type.equals(ProductsTypes.Otros)) {
            Other newOther = new Other(name, description, prize);
            products.add(newOther);
            result = true;
            items.addItem(newOther);
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

    //Este metodo devuelve el array de los productos pero sin duplicados, es decir listar Items.
    @Override
    public Set<Product> listAllProducts() {
        Set<Product> newList = new TreeSet<>();
        for (Product p : products) {
            newList.add(p);
        }
        return newList;
    }

    //Este metodo devuelve el array con TODOS los elementos inclusive los duplicados.
    @Override
    public List<Product> listAllProductsNoDuplicates() {
        List<Product> newList = new ArrayList<>();
        for (Product p : products) {
            newList.add(p);
        }
        return newList;
    }

    @Override
    public Set<Product> listAllProducts(Comparator c) {
        Set<Product> newList = null;

        Collections.sort(products, c);
        newList = (Set<Product>) products;

        return newList;
    }

    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        Set<Product> newList = new TreeSet<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().equals(type)) {
                newList.add(products.get(i));
            }
        }
        return newList;
    }

    @Override
    public Set<Product> listAllByName(String name) {
        Set<Product> newList = new TreeSet<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(name)) {
                newList.add(products.get(i));
            }
        }
        return newList;
    }

    @Override
    public Set<Product> listAllByName(String name, ProductsTypes type) {
        Set<Product> newList = new TreeSet<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name) && products.get(i).getType().equals(type)) {
                newList.add(products.get(i));
            }
        }
        return newList;
    }

    @Override
    public Set<Product> listAllByStatus(Product.Status status) {
        Set<Product> newList = new TreeSet<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getStatus().equals(status)) {
                newList.add(products.get(i));
            }
        }
        return newList;
    }

    public Product searchByKey(String key) {
        Product result = null;
        boolean aux = false;
        for (int i = 0; i < products.size() && !aux; i++) {
            if (products.get(i).getKey().equals(key)) {
                result = products.get(i);
                aux = true;
            }
        }
        return result;
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        Map<Product, Integer> newList = new TreeMap<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                newList.put(products.get(i), i);
            }
        }
        return newList;
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        Map<Product, Integer> newList = new TreeMap<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().equals(type) && products.get(i).getName().equals(name)) {
                newList.put(products.get(i), i);
            }
        }
        return newList;
    }

    @Override
    public List<Product> listAllDifferentProducts() {
        List<Product> newList = new ArrayList<>();
        Product aux = new Product();

        for (int i = 0; i < products.size(); i++) {
            if (aux.equals(products.get(i)) != true) {
                newList.add(products.get(i));
                aux = products.get(i);
            }
        }

        return newList;
    }

    @Override
    public List<Product> listAllDifferentMovies() {
        List<Product> newList = new ArrayList<>();
        for (Product p : products) {
            if (p.getType().equals(ProductsTypes.Peliculas)) {
                newList.add(p);
            }
        }
        return newList;
    }

    @Override
    public List<Product> listAllDifferentGames() {
        List<Product> newList = new ArrayList<>();
        for (Product p : products) {
            if (p.getType().equals(ProductsTypes.Juegos)) {
                newList.add(p);
            }
        }
        return newList;
    }

    public List<Product> listAllDifferentOther() {
        List<Product> newList = new ArrayList<>();
        for (Product p : products) {
            if (p.getType().equals(ProductsTypes.Otros)) {
                newList.add(p);
            }
        }
        return newList;
    }

    public boolean absoluteAddProduct(String name, String description, double prize, String key, Status status, ProductsTypes type) {
        boolean result = false;
        if (name == "") {
            name = "No Named Product";
        }
        Product p = new Product(name, description, prize, key, status, type);
        products.add(p);
        result = true;
        return result;
    }

    public boolean absoluteAddFilm(MovieCategory type, int minAge, String name, String description, double prize, String key, Status status) {
        boolean result = false;
        if (name == "") {
            name = "No Named Product";
        }
        Film f = new Film(type, minAge, name, description, prize, key, status);
        products.add(f);
        items.addItem(f);

        return result;
    }

    public boolean absoluteAddGame(GameCategory type, int minAge, String name, String description, double prize, String key, Status status) {
        boolean result = false;
        if (name == "") {
            name = "No Named Product";
        }
        Game g = new Game(type, minAge, name, description, prize, key, status);
        products.add(g);
        items.addItem(g);

        return result;
    }

    public boolean absoluteAddOther(String name, String description, double prize, String key, Status status) {
        boolean result = false;
        if (name == "") {
            name = "No Named Product";
        }
        Other o = new Other(name, description, prize, key, status);
        products.add(o);
        items.addItem(o);

        return result;
    }

}
