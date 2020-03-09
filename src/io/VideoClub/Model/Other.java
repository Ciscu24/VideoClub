package io.VideoClub.Model;

import io.VideoClub.Model.Enums.ProductsTypes;

public class Other extends Product{
    
    public Other(String name, String description, double prize) {
        super(name, description, prize, ProductsTypes.Otros);
    }
  
    public Other(String name, String description, double prize, String key, Status status){
        super(name, description, prize, key, status, ProductsTypes.Otros);
    }
 
    
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        
        if (o != null) {
            if (this == o) {
                result = true;
            } else {
                Game otro = (Game) o;
                result = (this.name.equals(otro.getName()));
            }
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
