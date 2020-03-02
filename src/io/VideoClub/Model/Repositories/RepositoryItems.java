package io.VideoClub.Model.Repositories;

import io.VideoClub.Model.Item;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class RepositoryItems implements Comparator<Item> {

    List<Item> items;

    public RepositoryItems() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean addItem(Item it) {
        boolean result = false;

        if (it != null) {
            result = items.add(it);
        }

        return result;
    }

    public boolean deleteItem(String nombre) {
        boolean result = false;
        Iterator<Item> i = items.iterator();

        if (nombre != null) {
            while (i.hasNext() && !result) {
                result = i.next().getName().toLowerCase().equals(nombre.toLowerCase());
                if (result) {
                    i.remove();
                }
            }
        }

        return result;
    }

    public boolean searchItem(String nombre) {
        boolean result = false;
        Iterator<Item> i = items.iterator();

        if (nombre != null) {
            while (i.hasNext() && !result) {
                result = i.next().getName().toLowerCase().equals(nombre.toLowerCase());
            }
        }

        return result;
    }

    public void writeItem() {
        sortItems();
        Iterator<Item> i = items.iterator();

        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }

    }

    public void sortItems() {
        items.sort(this);
    }

    @Override
    public int compare(Item o1, Item o2) {
        int result = 0;

        if (o1 != null && o2 != null) {
            result = o1.compareTo(o2);
        }

        return result;
    }

}
