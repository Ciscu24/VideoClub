package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;

public class Film extends Product{
    private MovieCategory type;
    private int minAge;
    

    public Film(){}

    public Film(MovieCategory type, int minAge, String name, String description, double prize) {
        super(name, description, prize);
        this.type = type;
        this.minAge = minAge;
    }
    
    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

  
    
    
    
}
