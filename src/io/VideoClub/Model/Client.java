/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author migue
 */
public class Client implements IClient{
    private String ID;
    private String Name;
    private java.time.LocalDateTime Time;
    private String Phone;
    private String User;
    private String password;

    public Client(String ID, String Name,String Phone, LocalDateTime Time) {
        this.ID = ID;
        this.Name = Name;
        this.Time = Time;
        this.Phone = Phone;
        this.Time = LocalDateTime.now();
        this.password=generateRandom16Chars();
    }
    
 
    public Client(String ID, String Name,String Phone, LocalDateTime Time, String User, String password) {
        this.ID = ID;
        this.Name = Name;
        this.Time = Time;
        this.Phone = Phone;
        this.Time = LocalDateTime.now();
        this.User = User;
        this.password = generateRandom16Chars();
    }
    private String generateRandom16Chars(){
        return(String)UUID.randomUUID().toString().subSequence(0, 16);
    }
    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    

    private Client() {
    }


    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public void setName(String n) {
        this.Name=n;
    }

    @Override
    public LocalDateTime getTime() {
        return Time;
    }

    @Override
    public void setTime(LocalDateTime t) {
        this.Time=t;
    }

    @Override
    public String getPhone() {
        return Phone;
    }

    @Override
    public void setPhone(String p) {
        this.Phone=p;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result=false;
        if(obj!=null){
            result=true;
        }else if(obj instanceof Client){
            Client aux=(Client)obj;
            return this.ID==aux.getID();
        }
        return result;
    }

    public int compareTo(Client c) {
       int result = 0;
       if(c!=null){
           if (this.Name.toLowerCase().equals(c.getName().toLowerCase())) {
                result = 0;
            } else if (this.Name.toLowerCase().compareTo(c.getName().toLowerCase()) > 0) {
                result = 1;
            } else if (this.Name.toLowerCase().compareTo(c.getName().toLowerCase()) < 0) {
                result = -1;
            }
       }
       return result;
    }

    @Override
    public String toString() {
        return "Client{" + "ID=" + ID + ", Name=" + Name + ", Time=" + Time + ", Phone=" + Phone + ", User=" + User + ", password=" + password + '}';
    }
    
    
    
}
