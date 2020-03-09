package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;

public class Film extends Product {

    private MovieCategory type;
    private int minAge;

    public Film() {
    }

    public Film(MovieCategory type, int minAge, String name, String description, double prize) {
        super(name, description, prize, ProductsTypes.Peliculas);
        this.type = type;
        this.minAge = minAge;
    }
    
    public Film(MovieCategory type, int minAge, String name, String description, double prize, String key, Status status){
        super(name, description, prize, key, status, ProductsTypes.Peliculas);
        this.type = type;
        this.minAge = minAge;
    }
    
    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public MovieCategory getTypeMovie(){
        return type;
    }

    public void setTypeMovie(MovieCategory type) {
        this.type = type;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if (o != null) {
            if (this == o) {
                result = true;
            } else {
                Film otro = (Film) o;
                result = (this.name.equals(otro.getName()) & this.type.equals(otro.getType()));
            }
        }

        return result;
    }

    @Override
    public String toString() {
        System.out.println("Soy una pelicula");
        return super.toString()+"\nGénero de la película: "+type+"\nEdad mínima: "+minAge+"\n----------------------";
    }
}
