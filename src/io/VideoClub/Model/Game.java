package io.VideoClub.Model;

import io.VideoClub.Model.Enums.GameCategory;

public class Game extends Product{
    private GameCategory type;
    private int minAge;

    public Game(){}

    public Game(GameCategory type, int minAge, String name, String description, double prize) {
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
