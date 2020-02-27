package io.VideoClub.Model;

import io.VideoClub.Model.Repositories.RepositoryItems;
import java.util.Iterator;

public abstract class Item implements Comparable<Item> {

    protected String name;
    protected String description;
    protected double prize;

    public Item() {
    }

    public Item(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    /*public boolean addItem(Item it) {
    boolean result = false;
    
    if (it != null) {
    result = items.getItems().add(it);
    System.out.println(result);
    if (result) {
    items.setItems(items.getItems());
    }
    }
    
    return result;
    }
    
    public boolean deleteItem(Item it) {
    boolean result = false;
    
    if (it != null && items.getItems().contains(it)) {
    result = items.getItems().remove(it);
    }
    
    return result;
    }
    
    public boolean searchItem(Item it) {
    boolean result = false;
    
    if (it != null) {
    result = items.getItems().contains(it);
    }
    
    return result;
    }
    
    public void writeItem() {
    Iterator<Item> i = items.getItems().iterator();
    
    while (i.hasNext()) {
    System.out.println(i.next().toString());
    }
    
    }*/

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj != null) {
            if (this == obj) {
                result = true;
            } else {
                Item otro = (Item) obj;
                result = this.name.equals(otro.getName());
            }
        }

        return result;
    }

    @Override
    public int compareTo(Item o) {
        int result = 0;

        if (o != null) {
            if (this.name.toLowerCase().equals(o.getName().toLowerCase())) {
                result = 0;
            } else if (this.name.toLowerCase().compareTo(o.getName().toLowerCase()) == 1) {
                result = 1;
            } else if (this.name.toLowerCase().compareTo(o.getName().toLowerCase()) == 0) {
                result = -1;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", description=" + description + ", prize=" + prize + '}';
    }

}
