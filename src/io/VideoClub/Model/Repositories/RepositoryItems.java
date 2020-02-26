package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Item;
import java.util.Iterator;
import java.util.TreeSet;

public class RepositoryItems {

    TreeSet<Item> items;

    public RepositoryItems() {
        items = new TreeSet<>();
    }

    public TreeSet<Item> getItems() {
        return items;
    }

    public void setItems(TreeSet<Item> items) {
        this.items = items;
    }
    
    public boolean addItem(Item it) {
        boolean result = false;

        if (it != null) {
            result = items.add(it);
        }

        return result;
    }
    
    public boolean deleteItem(Item it) {
        boolean result = false;

        if (it != null && searchItem(it)) {
            result = items.remove(it);
        }

        return result;
    }
    
    public boolean searchItem(Item it) {
        boolean result = false;

        if (it != null) {
            result = items.contains(it);
        }

        return result;
    }
    
    public void writeItem() {
        Iterator<Item> i = items.iterator();

        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }

    }

}
