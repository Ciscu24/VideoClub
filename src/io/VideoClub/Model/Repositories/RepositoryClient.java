/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model.Repositories;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Client;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator ;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.IClient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author migue
 */
public class RepositoryClient implements Comparator<Client>, IRepositoryClient{
    private List<Client> clientL;
    
    public RepositoryClient(){
        clientL=new ArrayList<>();
    }

    public boolean addClient(Client c){
        return this.clientL.add(c);
    }
    
    public boolean addClient(String Name,String Phone, String user, String password){
        Client c = new Client(Name, Phone, LocalDateTime.now(), user, password);        
        return this.clientL.add(c);
    }
    
    @Override
    public boolean removeClient(String ID){
        boolean result=false;
        for(int i=0;i<clientL.size();i++){
            if(clientL.get(i).getID().equals(ID)){
                clientL.remove(i--);
                result=true;
            }
        }
     return result;
    }
    public boolean removeClient(Client c){
        return this.clientL.contains(c);
    }
    public boolean searchpassword(String psw){
        boolean result=false;
            Iterator<Client> i = clientL.iterator();
                if (psw != null) {
            while (i.hasNext() && !result) {
                result = i.next().getPassword().equals(psw);
            }
        }
        return result;
        
    }
    
    public boolean searchUser(String c){
        boolean result=false;
        Iterator<Client> i = clientL.iterator();
        if (c != null) {
            while (i.hasNext() && !result) {
                result = i.next().getUser().equals(c);
            }
        }
        return result;
    }
    
    public Client devolverCliente(String usuario){
    Client result=null;
    boolean valid = false;
    for(int i=0; i<clientL.size() && !valid; i++){
        if(clientL.get(i).getUser().equals(usuario)){
            result = clientL.get(i);
            valid = true;
        }
    }
    return result;
    } 
    
    public String showClient(){
        String result="";
        for(Client c:clientL){
            result+=c;
        }
        return result;
    }

    @Override
    public int compare(Client o1, Client o2) {
        int result = 0;
        if (o1 != null && o2 != null) {
            result = o1.compareTo(o2);
        }
        return result;
    }

    @Override
    public Set<IClient> listAllClients() {
        Set<IClient> list = new TreeSet<>();
        for (Client C : clientL) {
            list.add(C);
        }
        return list;
    }

    @Override
    public Set<IClient> listAllClients(Comparator c) {
      Set<IClient> list = new TreeSet<>();
      list.addAll(clientL);
      return list;
    }

    @Override
    public Set<IClient> listAllClientsWithReservations() {
        return null;
    }

    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        boolean result=false;
        if (id!=null) {
            Client NewClient = new Client(name, phone, time);
            result = true;
        }
        return result;
    }

    @Override
    public boolean editClient(IClient c) {
                boolean result = false;
        if (c !=null) {
            for (int i=0;i<clientL.size()&&!result;i++) {
                if (clientL.get(i).equals(c)) {
                    clientL.set(i, (Client) c);
                    result = true;
                }
            }
        }
        return result;
    }
    
}
