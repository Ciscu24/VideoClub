package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Ejemplares;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IAppController {
    public final static String catalogDDBB="catalog.xml";
    public final static String clientsDDBB="clients.xml";
    public final static String reservationsDDBB="reservations.xml";
    
    
    Set<Ejemplares> listAllProducts();
    Set<Ejemplares> listAllProducts(Comparator c);
    Set<Ejemplares> listAllByType(ProductsTypes type);
    Set<Ejemplares> listAllByName(String name);
    Set<Ejemplares> listAllByName(String name,ProductsTypes type);
    Set<Ejemplares> listAllByStatus(Ejemplares.Status status);
    
    List<Ejemplares> listAllDifferentProducts();
    List<Ejemplares> listAllDifferentMovies();
    List<Ejemplares> listAllDifferentGames();
    
    Map<Ejemplares,Integer> listAllAmountOfProducts(String name); 
    Map<Ejemplares,Integer> listAllAmountOfProducts(ProductsTypes type,String name);
    
    Set<IClient> listAllClients();
    Set<IClient> listAllClients(Comparator c);
    Set<IClient> listAllClientsWithReservationsNotFinished();
    
    Set<Reservation> listAllReservations();
    Set<Reservation> listAllReservations(Comparator c);
    Set<Reservation> listAllReservations(Reservation.StatusReserve status);
    
    double getIncommings();
    double getIncommings(LocalDate from);
    double getIncommings(LocalDate from, LocalDate to);
    Map<IClient,Double> resumeAllIncomingsByClient();
    
    boolean createProduct(String name, String description,double prize);
    boolean createMovie(ProductsTypes type,String name, String description, MovieCategory cat,int minAge);
    boolean createGame(ProductsTypes type, String name, String description, GameCategory cat,int minAge);
    
    boolean createClient(String id,String name,String phone,LocalDateTime time);
    boolean removeClient(String id);  //if has reservations cant be deleted
    boolean editClient(IClient e);
    
    boolean addProduct(String name);
    boolean removeProduct(String name);
    
    boolean editProduct(String key, Ejemplares newP);
    
    Ejemplares isAvailableProduct(String name);  //get product if yes
    boolean reserveProduct(Ejemplares prod,IClient client);
    double closeReservation();  //-->> status finished  --> get prizetopay
    
    boolean loadCatalogFromDDBB();  //XML or JSON
    boolean loadClientsFromDDBB();
    boolean loadReservationsFromDDBB();
    boolean loadAllDDBB();
    
    boolean saveCatalogFromDDBB();  //XML or JSON
    boolean saveClientsFromDDBB();
    boolean saveReservationsFromDDBB();
    boolean saveAllDDBB();
    
}
