/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;

/**
 *
 * @author migue
 */
public class Client implements IClient{
    private String ID;
    private String Name;
    private LocalDateTime Time;
    private String Phone;
    private Client User;
    private String password;

    public Client(String ID, String Name, LocalDateTime Time, String Phone, Client User, String password) {
        this.ID = ID;
        this.Name = Name;
        this.Time = Time;
        this.Phone = Phone;
        this.User = User;
        this.password = password;
    }

    public Client getUser() {
        return User;
    }

    public void setUser(Client User) {
        this.User = User;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int compareTo(Client o2) {
       int result = 0;
       if(o2!=null){
           if (this.Name.toLowerCase().equals(o2.getName().toLowerCase())) {
                result = 0;
            } else if (this.Name.toLowerCase().compareTo(o2.getName().toLowerCase()) > 0) {
                result = 1;
            } else if (this.Name.toLowerCase().compareTo(o2.getName().toLowerCase()) < 0) {
                result = -1;
            }
       }
       return result;
    }
    
}
