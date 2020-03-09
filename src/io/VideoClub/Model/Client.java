/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;
import java.util.Comparator;

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
    
}
