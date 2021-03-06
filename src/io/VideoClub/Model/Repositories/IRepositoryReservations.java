package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public interface IRepositoryReservations {
    
    boolean reserveProduct(Product prod,IClient client);
    boolean returnedProduct(Product prod, IClient client);
    
    Set<Reservation> listAllReservations();
    Set<Reservation> listAllReservations(Comparator c);
    Set<Reservation> listAllReservations(Reservation.StatusReserve status);
    
    double getIncommings();
    double getIncommings(LocalDate from);
    double getIncommings(LocalDate from, LocalDate to);
    Map<IClient,Double> resumeAllIncomingsByClient();
    
    Product isAvailableProduct(String name);
    double closeReservation(Reservation r);  //-->> status finished  --> get prizetopay
}
