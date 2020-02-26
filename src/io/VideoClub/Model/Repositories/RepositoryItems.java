package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Item;
import java.util.Set;
import java.util.TreeSet;

public class RepositoryItems {
    
    Set<Item> items;

    public RepositoryItems() {
        items=new TreeSet<>();
    }
    
}
