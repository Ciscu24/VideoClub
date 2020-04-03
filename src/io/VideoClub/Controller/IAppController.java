package io.VideoClub.Controller;

public interface IAppController {
    public final static String catalogDDBB="catalog.xml";
    public final static String clientsDDBB="clients.xml";
    public final static String reservationsDDBB="reservations.xml";
    
    boolean loadCatalogFromDDBB();  //XML or JSON
    boolean loadClientsFromDDBB();
    boolean loadReservationsFromDDBB();
    boolean loadAllDDBB();
    
    boolean saveCatalogFromDDBB();  //XML or JSON
    boolean saveClientsFromDDBB();
    boolean saveReservationsFromDDBB();
    boolean saveAllDDBB();
    
}
