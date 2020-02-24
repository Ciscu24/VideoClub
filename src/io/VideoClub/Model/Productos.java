package io.VideoClub.Model;


public abstract class Productos {
     protected String name;
     protected String description;
     protected double prize;
    
    public Productos(){}
    public Productos(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }
}
