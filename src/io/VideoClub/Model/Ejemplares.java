/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.util.UUID;

public abstract class Ejemplares extends Productos implements Cloneable{
    public enum Status{
        AVAILABLE,
        RESERVED
    }
    private String key;
    private Status status;
   
    
    
    public Ejemplares(){}
    public Ejemplares(String name, String description,double prize){
        super(name,description,prize);
        this.key=generateRandom16Chars();
    }
    
    private String generateRandom16Chars(){
        return(String)UUID.randomUUID().toString().subSequence(0, 16);
    }
    
    public boolean equals(Object o){
        boolean result=false;
        if(o!=null){
            if(o instanceof Ejemplares){
                Ejemplares other=(Ejemplares)o;
                if(other.key.equals(other.key)){
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Ejemplares clone=(Ejemplares)super.clone(); //To change body of generated methods, choose Tools | Templates.
        clone.key=generateRandom16Chars();
        return (Object)clone;
    }
    
    
    
}
