package io.VideoClub.Model;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.ProductsTypes;

public class Game extends Product {
    
    private GameCategory type;
    private int minAge;
    
    public Game() {
    }
    
    public Game(GameCategory type, int minAge, String name, String description, double prize) {
        super(name, description, prize);
        this.type = type;
        this.minAge = minAge;
    }
    
    //Editar
    public Game(GameCategory type, int minAge, String name, String description, double prize, String key, Status status, ProductsTypes ptype) {
        super(name, description, prize, key, status, ptype);
        this.type = type;
        this.minAge = minAge;
    }
    
    public int getMinAge() {
        return minAge;
    }
    
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        
        if (o != null) {
            if (this == o) {
                result = true;
            } else {
                Game otro = (Game) o;
                result = (this.name.equals(otro.getName()) & this.type.equals(otro.getType()));
            }
        }
        
        return result;
    }
    
}
