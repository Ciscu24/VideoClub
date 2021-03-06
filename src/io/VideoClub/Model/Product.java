package io.VideoClub.Model;

import io.VideoClub.Model.Enums.ProductsTypes;
import java.util.UUID;

public class Product extends Item implements Cloneable{
    public enum Status{
        AVAILABLE,
        RESERVED
    }
    private String key;
    private Status status; 
    private ProductsTypes type;
    
    
    public Product(){}
    public Product(String name, String description,double prize, ProductsTypes type){
        super(name,description,prize);
        this.key=generateRandom16Chars();
        this.status = Status.AVAILABLE;
        this.type = type;
    }
    
    //Constructor full para el XML
    public Product(String name, String description,double prize, String key, Status status, ProductsTypes type){
        super(name,description,prize);
        this.key=key;
        this.status = status;
        this.type = type;
    }
    
    public Product(String name){
        super(name, "Unknown", 0);
        this.key=generateRandom16Chars();
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProductsTypes getType() {
        return type;
    }

    public void setType(ProductsTypes type) {
        this.type = type;
    }
       
    private String generateRandom16Chars(){
        return(String)UUID.randomUUID().toString().subSequence(0, 16);
    }
    
    public boolean equals(Object o){
        boolean result=false;
        if(o!=null){
            if(o instanceof Product){
                Product other=(Product)o;
                if(this.key.equals(other.key)){
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Product clone=(Product)super.clone(); //To change body of generated methods, choose Tools | Templates.
        clone.key=generateRandom16Chars();
        return (Object)clone;
    }
    
    @Override
    public String toString() {
        return "\n------ "+name+" ------\nKey: "+key+"\nDescription: "+description+"\nPrize: "+prize+"\nStatus: "+status;
    }
}
