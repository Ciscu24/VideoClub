package io.VideoClub.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    
    protected String name;
    protected String description;
    protected double prize;
    
    public Item() {
    }
    
    public Item(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrize() {
        return prize;
    }
    
    public void setPrize(double prize) {
        this.prize = prize;
    }

    //HABLAR MAÑANA SOBRE ESTO
    public boolean addItem(Item it, List<Item> its) {
        boolean result = false;
        
        if (it != null) {
            its.add(it);
            result = true;
        }
        
        return result;
    }
    
    public boolean deleteItem(Item it, List<Item> its) {
        boolean result = false;
        
        if (it != null && its.contains(it)) {
            its.remove(it);
        }
        
        return result;
    }

    /*public boolean searchItem(String nombre, ArrayList<Item> its) {
    boolean result = false;
    
    if (nombre != null) {
    for (int i = 0; i < its.size() && !result; i++) {
    if (nombre.equals(its)) {
    result = true;
    }
    }
    }
    
    return result;
    }*/
    public boolean searchItem(Item it, List<Item> its) {
        boolean result = false;
        
        if (it != null) {
            if (its.contains(it)) {
                result = true;
            }
        }
        
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        
        if (obj != null) {
            if (this == obj) {
                result = true;
            } else {
                Item otro = (Item) obj;
                result = this.name.equals(otro.getName());
            }
        }
        
        return result;
    }
    
}
