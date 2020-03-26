package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RepositoryReservations implements IRepositoryReservations {

    public List<Reservation> reservations;

    public RepositoryReservations() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public Set<Reservation> listAllReservations() {
        Set<Reservation> newList = new TreeSet<>();
        for (Reservation r : reservations) {
            newList.add(r);
        }
        return newList;
    }

    @Override
    public Set<Reservation> listAllReservations(Comparator c) {
        Set<Reservation> newList = null;
        
        Collections.sort(reservations, c);
        newList = (Set<Reservation>) reservations;
        
        return newList;
    }

    @Override
    public Set<Reservation> listAllReservations(Reservation.StatusReserve status) {
        Set<Reservation> newList = new TreeSet<>();

        for (Reservation r : reservations) {
            if (r.status.equals(Reservation.StatusReserve.ACTIVE)) {
                newList.add(r);
            }
        }

        return newList;
    }

    @Override
    public double getIncommings() {
        double result = 0;

        for (Reservation r : reservations) {
            result += r.pro.getPrize();
        }

        return result;
    }

    @Override
    public double getIncommings(LocalDate from) {
        double result = 0;
        
        for(Reservation r : reservations){
            if(r.ini.isAfter(from)){
                result += r.pro.getPrize();
            }
        }
        return result;
    }

    @Override
    public double getIncommings(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<IClient, Double> resumeAllIncomingsByClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double closeReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product isAvailableProduct(String name) {
        Product result = null;
        Product aux = null;
        Iterator<Product> i = products.iterator();

        if (name != null) {
            while (i.hasNext()) {
                aux = i.next();
                if (aux.getName().toLowerCase().equals(name.toLowerCase())
                        && aux.getStatus().equals(Product.Status.AVAILABLE)) {
                    result = aux;
                }
            }
        }

        return result;
    }

    @Override
    public boolean reserveProduct(Product prod, IClient client) {
        boolean result = false;

        if (prod != null && client != null && isAvailableProduct(prod.getName()) != null) {
            reservations.add(new Reservation(prod, client));
        }

        return result;
    }

    //NO TERMINADO, PREGUNTAR CARLOS SOBRE LO DEL TIEMPO
    @Override
    public boolean returnedProduct(Product prod, IClient client) {
        boolean result = false;
        Iterator<Product> iP = products.iterator();
        Product auxP = null;

        if (prod != null && client != null) {
            while (iP.hasNext()) {
                auxP = iP.next();
                for (int i = 0; i < reservations.size(); i++) {
                    if (reservations.get(i).pro.equals(prod)
                            && reservations.get(i).equals(client)) {
                        reservations.get(i).status = Reservation.StatusReserve.FINISHED;
                        result = true;
                    }
                }
            }
        }

        return result;
    }
}
