package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Client;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

        for (Reservation r : reservations) {
            if (r.ini.isAfter(from)) {
                result += r.pro.getPrize();
            }
        }
        return result;
    }

    @Override
    public double getIncommings(LocalDate from, LocalDate to) {
        double result = 0;

        for (Reservation r : reservations) {
            if (r.ini.isAfter(from) && r.end.isBefore(to)) {
                result += r.pro.getPrize();
            }
        }

        return result;
    }

    @Override
    public Map<IClient, Double> resumeAllIncomingsByClient() {
        Map<IClient, Double> newList = new TreeMap<>();

        for (Reservation r : reservations) {
            newList.put(r.cli, r.pro.getPrize());
        }

        return newList;
    }

    @Override
    public double closeReservation(Reservation r) {
        double result = 0;
        if (r != null) {
            result = r.pro.getPrize();
            r.status = Reservation.StatusReserve.FINISHED;
        }
        return result;
    }

    @Override
    public Product isAvailableProduct(String name) {
        Product result = null;

        for (Reservation r : reservations) {
            if (name != null
                    && r.pro.getName().toLowerCase().equals(name.toLowerCase())
                    && r.pro.getStatus().equals(Product.Status.AVAILABLE)) {
                result = r.pro;
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

    @Override
    public boolean returnedProduct(Product prod, IClient client) {
        boolean result = false;

        if (prod != null && client != null) {
            for (Reservation r : reservations) {
                if (r.pro.equals(prod) && r.cli.equals(client) 
                        && r.status.equals(Reservation.StatusReserve.FINISHED)) {
                    result = true;
                }
            }
        }

        return result;
    }
    
    public List<Reservation> productoReservadoCliente(Client cliente){
        
    }
}
