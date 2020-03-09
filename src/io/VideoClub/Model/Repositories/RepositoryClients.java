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
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author migue
 */
public class RepositoryClients implements Comparator<Client>, IRepositoryClient{
    private List<Client> clientL;
   
    
    public boolean addClient(Client c){
        return this.clientL.add(c);
    }
    
    
    
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
    public boolean searchUser(Client c){
        boolean result=false;
         Iterator<Client> i = clientL.iterator();
                if (c != null) {
            while (i.hasNext() && !result) {
                result = i.next().getPassword().equals(c);
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
      Set<IClient> aux = new TreeSet<>();
      aux.addAll(clientL);
      return aux;
    }

    @Override
    public Set<IClient> listAllClientsWithReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        boolean result=false;
        if (id!=null) {
            Client NewClient = new Client(id, name, phone, time);
            result = true;
        }
        return result;
    }

    @Override
    public boolean editClient(IClient e) {
                boolean result = false;
        if (e != null) {
            for (int i = 0; i < clientL.size() && !result; i++) {
                if (clientL.get(i).equals(e)) {
                    clientL.set(i, (Client) e);
                    result = true;
                }
            }
        }
        return result;
    }
    
}
