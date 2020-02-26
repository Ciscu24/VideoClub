package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.IClient;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;

public interface IRepositoryClient {
    Set<IClient> listAllClients();
    Set<IClient> listAllClients(Comparator c);
    Set<IClient> listAllClientsWithReservationsNotFinished();
    
    boolean createClient(String id,String name,String phone,LocalDateTime time);
    boolean removeClient(String id);  //if has reservations cant be deleted
    boolean editClient(IClient e);
}
